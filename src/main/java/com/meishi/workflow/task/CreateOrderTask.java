package com.meishi.workflow.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.meishi.model.Administrator;
import com.meishi.model.Customer;
import com.meishi.model.Dish;
import com.meishi.model.Location;
import com.meishi.model.Order;
import com.meishi.model.OrderStatus;
import com.meishi.model.OrderStatusEntry;
import com.meishi.repository.OrderRepository;

@Component
public class CreateOrderTask implements JavaDelegate {
	
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
		List<Dish> foods = new ArrayList<Dish>();
		Dish meishi1 = new Dish();
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
