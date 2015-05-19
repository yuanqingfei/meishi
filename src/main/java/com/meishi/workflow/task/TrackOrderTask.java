package com.meishi.workflow.task;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.meishi.workflow.model.Cook;
import com.meishi.workflow.model.Order;
import com.meishi.workflow.model.OrderStatus;
import com.meishi.workflow.model.OrderStatusEntry;
import com.meishi.workflow.model.Transporter;
import com.meishi.workflow.repository.OrderRepository;

@Component
public class TrackOrderTask {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private OrderRepository orderRepo;

	private DelegateExecution execution;

	public void updateOrderStatusToCooking(DelegateExecution execution) {
		this.execution = execution;
		updateOrderCook();
		updateOrderStatusTo(OrderStatus.COOKING);
	}

	private void updateOrderCook() {
		Order order = getOrder();
		Cook cook = (Cook)execution.getVariable("cook");
		order.setCook(cook);
		orderRepo.save(order);
	}

	public void updateOrderStatusToCooked(DelegateExecution execution) {
		this.execution = execution;
		updateOrderStatusTo(OrderStatus.COOKED);
	}

	public void updateOrderStatusToSending(DelegateExecution execution) {
		this.execution = execution;
		updateOrderSender();
		updateOrderStatusTo(OrderStatus.SENDING);
	}

	private void updateOrderSender() {
		Order order = getOrder();
		Transporter sender = (Transporter)execution.getVariable("sender");
		order.setTransporter(sender);
		orderRepo.save(order);
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
		Assert.notNull(orderRepo);
		Assert.notNull(execution);

		Order order = getOrder();
		
		OrderStatusEntry entry = new OrderStatusEntry();
		entry.setStatus(status);
		entry.setTime(new Date());
		order.getStatuses().add(entry);

		orderRepo.save(order);
		logger.info("update order status to: " + status);
	}

	private Order getOrder() {
		String orderId = (String) execution.getVariable("orderId");
		Assert.notNull(orderId);
		Order order = orderRepo.findOne(orderId);
		return order;
	}

}
