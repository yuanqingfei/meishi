package com.meishi.workflow.task;

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
import com.meishi.service.AdministratorService;
import com.meishi.service.CookService;
import com.meishi.service.MoneyService;
import com.meishi.service.OrderService;
import com.meishi.service.SenderService;
import com.meishi.util.Constants;

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

	@Autowired
	private MoneyService moneyService;

	private DelegateExecution execution;

	public void updateOrderStatusToCooking(DelegateExecution execution) {
		this.execution = execution;

		List<Cook> cooks = occupyCooks();

		Order order = getOrder();
		order.setCooks(cooks);
		orderService.upsert(order);
		updateOrderStatusTo(OrderStatus.COOKING);
	}

	public void updateOrderStatusToCooked(DelegateExecution execution) {
		this.execution = execution;
		releaseCooks();
		updateOrderStatusTo(OrderStatus.COOKED);
	}

	public void updateOrderStatusToSending(DelegateExecution execution) {
		this.execution = execution;
		Order order = getOrder();
		List<Sender> senders = occupySenders();
		order.setSenders(senders);
		orderService.upsert(order);
		updateOrderStatusTo(OrderStatus.SENDING);
	}

	public void updateOrderStatusToSent(DelegateExecution execution) {
		this.execution = execution;
		releaseSenders();
		updateOrderStatusTo(OrderStatus.SENT);
	}

	public void updateOrderStatusToSuccess(DelegateExecution execution) {
		this.execution = execution;
		updateOrderStatusTo(OrderStatus.SUCCESS);
	}

	// if fail, need refund and release all related resource.
	public void updateOrderStatusToFailure(DelegateExecution execution) {
		this.execution = execution;

		refund();
		releaseCooks();
		releaseSenders();

		updateOrderStatusTo(OrderStatus.FAILURE);
	}

	private List<Cook> occupyCooks() {
		List<Cook> cooks = (List<Cook>) execution.getVariable(Constants.COOK_VARIABLE);
		for (Cook cook : cooks) {
			cookService.occupy(cook.getIdentity());
		}
		return cooks;
	}

	private void releaseCooks() {
		List<Cook> cooks = (List<Cook>) execution.getVariable(Constants.COOK_VARIABLE);
		if (cooks == null)
			return;
		for (Cook cook : cooks) {
			cookService.release(cook.getIdentity());
		}
	}

	private List<Sender> occupySenders() {
		List<Sender> senders = (List<Sender>) execution.getVariable(Constants.SENDER_VARIABLE);
		for (Sender sender : senders) {
			senderService.occupy(sender.getIdentity());
		}
		return senders;
	}

	private void releaseSenders() {
		List<Sender> senders = (List<Sender>) execution.getVariable(Constants.SENDER_VARIABLE);
		if (senders == null)
			return;
		for (Sender sender : senders) {
			senderService.release(sender.getIdentity());
		}
	}

	private void refund() {
		String clientId = (String) execution.getVariable(Constants.CLIENT_ID_VARIABLE);
		moneyService.deposit(clientId, (Double) execution.getVariable(Constants.TOTAL_PRICE_VARIABLE));
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
		return orderService.getOne((String) execution.getVariable(Constants.ORDER_ID_VARIABLE));
	}

}
