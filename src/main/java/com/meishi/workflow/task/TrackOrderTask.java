package com.meishi.workflow.task;

import java.util.Date;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.meishi.model.Administrator;
import com.meishi.model.Cook;
import com.meishi.model.Order;
import com.meishi.model.OrderStatus;
import com.meishi.model.OrderStatusEntry;
import com.meishi.model.Sender;
import com.meishi.service.AdministratorService;
import com.meishi.service.CookService;
import com.meishi.service.OrderService;
import com.meishi.service.SenderService;

@Component
public class TrackOrderTask {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private OrderService orderService;

	@Autowired
	private CookService cookService;
	
	@Autowired
	private SenderService senderService;
	
	@Autowired
	private AdministratorService adminService;

	private DelegateExecution execution;

	public void updateOrderStatusToCooking(DelegateExecution execution) {
		this.execution = execution;
		Order order = getOrder();
		List<Cook> cooks = (List<Cook>) execution.getVariable("cooks");
		for (Cook cook : cooks) {
			cookService.occupy(cook.getIdentity());
		}
		order.setCooks(cooks);
		orderService.upsert(order);
		updateOrderStatusTo(OrderStatus.COOKING);
	}


	public void updateOrderStatusToCooked(DelegateExecution execution) {
		this.execution = execution;
		List<Cook> cooks = (List<Cook>) execution.getVariable("cooks");
		for (Cook cook : cooks) {
			cookService.release(cook.getIdentity());
		}
		updateOrderStatusTo(OrderStatus.COOKED);
	}

	public void updateOrderStatusToSending(DelegateExecution execution) {
		this.execution = execution;
		Order order = getOrder();
		List<Sender> senders = (List<Sender>) execution.getVariable("senders");
		for(Sender sender : senders){
			senderService.occupy(sender.getIdentity());
		}
		order.setSenders(senders);
		orderService.upsert(order);
		updateOrderStatusTo(OrderStatus.SENDING);
	}

	public void updateOrderStatusToSent(DelegateExecution execution) {
		this.execution = execution;
		List<Sender> senders = (List<Sender>) execution.getVariable("senders");
		for (Sender sender : senders) {
			cookService.release(sender.getIdentity());
		}
		updateOrderStatusTo(OrderStatus.SENT);
	}

	public void updateOrderStatusToSuccess(DelegateExecution execution) {
		this.execution = execution;
		Administrator admin = (Administrator) execution.getVariable("admin");
		adminService.release(admin.getIdentity());
		updateOrderStatusTo(OrderStatus.SUCCESS);
	}

	public void updateOrderStatusToFailure(DelegateExecution execution) {
		this.execution = execution;
		Administrator admin = (Administrator) execution.getVariable("admin");
		adminService.release(admin.getIdentity());
		updateOrderStatusTo(OrderStatus.FAILURE);
	}


	private void updateOrderStatusTo(OrderStatus status) {
		Assert.notNull(orderService);
		Assert.notNull(execution);

		Order order = getOrder();

		OrderStatusEntry entry = new OrderStatusEntry();
		entry.setStatus(status);
		entry.setTime(new Date());
		order.getStatuses().add(entry);

		orderService.upsert(order);
		logger.info("update order status to: " + status);
	}

	private Order getOrder() {
		String orderId = (String) execution.getVariable("orderId");
		return orderService.getOne(orderId);
	}

}
