package com.meishi.workflow.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.meishi.workflow.model.Administrator;
import com.meishi.workflow.model.Customer;
import com.meishi.workflow.model.Location;
import com.meishi.workflow.model.Meishi;
import com.meishi.workflow.model.Order;
import com.meishi.workflow.model.OrderStatus;
import com.meishi.workflow.model.OrderStatusEntry;
import com.meishi.workflow.repository.OrderRepository;

@Component
public class OrderCreator implements JavaDelegate {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private OrderRepository orderRepo;
	
	public OrderRepository getOrderRepo() {
		return orderRepo;
	}

	public void setOrderRepo(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		
		Assert.notNull(orderRepo);
		
		Administrator admin=(Administrator)exec.getVariable("admin");
		logger.info("admin: " + admin);
		String meishiList=(String)exec.getVariable("meishiList");
		logger.info("meishiList: " + meishiList);
		String clientLocation = (String)exec.getVariable("clientLocation");
		logger.info("clientLocation: " + clientLocation);
		String clientId = (String)exec.getVariable("clientId");
		logger.info("clientId: " + clientId);
		
//		status = Prepared
		
		// create an order based on above
		Order order = new Order();
		order.setAdministrator(admin);
		List<Meishi> foods = new ArrayList<Meishi>();
		Meishi meishi1 = new Meishi();
		meishi1.setName(meishiList);
		foods.add(meishi1);
		order.setFoods(foods);
		Customer orderBy = new Customer();
		Location location = new Location();
		location.setStreetName(clientLocation);
//		orderBy.setLocation(location);
		orderBy.setName("Real_Afei");
		orderBy.setIdentity(clientId);
		order.setCustomer(orderBy);
//		Producer cookBy = new Producer();
//		cookBy.setName("Bfei");
//		order.setCookBy(cookBy);
//		Transporter sendBy = new Transporter();
//		sendBy.setName("Cfei");
//		order.setSendBy(sendBy);
		
		List<OrderStatusEntry> statuses = new ArrayList<OrderStatusEntry>();
		OrderStatusEntry entry = new OrderStatusEntry();
		entry.setStatus(OrderStatus.CREATED);
		entry.setTime(new Date());
		statuses.add(entry);
		order.setStatuses(statuses);
		
		Date orderTime = new Date();
		order.setOrderTime(orderTime);
		
		orderRepo.save(order);

		Order existedOrder = orderRepo.findByCustomer_IdentityAndOrderTime(clientId, orderTime);
		Assert.notNull(existedOrder);
		exec.setVariable("orderId", existedOrder.getId());
		
		logger.info("create an order successfully");

	}

}
