package com.meishi.workflow.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.meishi.model.Cook;
import com.meishi.model.Order;
import com.meishi.model.OrderStatus;
import com.meishi.model.OrderStatusEntry;
import com.meishi.model.Sender;
import com.meishi.model.WorkerStatus;
import com.meishi.service.CookService;
import com.meishi.service.OrderService;

@Component
public class TrackOrderTask {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CookService cookService;

	private DelegateExecution execution;

	public void updateOrderStatusToCooking(DelegateExecution execution) {
		this.execution = execution;
		updateOrderCook();
		updateOrderStatusTo(OrderStatus.COOKING);
	}

	private void updateOrderCook() {
		Order order = getOrder();
		List<Cook> cooks = (List<Cook>) execution.getVariable("cooks");
		order.setCooks(new ArrayList<Cook>(cooks));
		orderService.upsert(order);
	}

	public void updateOrderStatusToCooked(DelegateExecution execution) {
		this.execution = execution;
		List<Cook> cooks = (List<Cook>) execution.getVariable("cooks");
		for(Cook cook : cooks){
			cook.setStatus(WorkerStatus.READY);
			cookService.upsert(cook);
		}
		updateOrderStatusTo(OrderStatus.COOKED);
	}

	public void updateOrderStatusToSending(DelegateExecution execution) {
		this.execution = execution;
		updateOrderSender();
		updateOrderStatusTo(OrderStatus.SENDING);
	}

	private void updateOrderSender() {
		Order order = getOrder();
		Sender sender = (Sender) execution.getVariable("sender");
		List<Sender> senders = new ArrayList<Sender>();
		senders.add(sender);
		order.setSenders(senders);
		orderService.upsert(order);
	}

	public void updateOrderStatusToSent(DelegateExecution execution) {
		this.execution = execution;
		updateOrderStatusTo(OrderStatus.SENT);
	}

	public void updateOrderStatusToSuccess(DelegateExecution execution) {
		this.execution = execution;
		updateOrderStatusTo(OrderStatus.SUCCESS);
	}

	public void updateOrderStatusToFailure(DelegateExecution execution) {
		this.execution = execution;
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
		Order order = orderService.getOne(orderId);
		return order;
	}

}
