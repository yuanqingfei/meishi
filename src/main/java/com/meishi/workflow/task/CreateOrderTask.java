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
import com.meishi.model.Order;
import com.meishi.model.OrderStatus;
import com.meishi.model.OrderStatusEntry;
import com.meishi.service.CustomerService;
import com.meishi.service.OrderService;

@Component
public class CreateOrderTask implements JavaDelegate {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	


	@Override
	public void execute(DelegateExecution exec) throws Exception {
		Administrator admin=(Administrator)exec.getVariable("admin");
		String meishiList=(String)exec.getVariable("meishiList");
		String clientId = (String)exec.getVariable("clientId");

		Order order = new Order();
		order.setAdministrator(admin);
		List<Dish> foods = new ArrayList<Dish>();
		Dish meishi1 = new Dish();
		meishi1.setName(meishiList);
		foods.add(meishi1);
		order.setFoods(foods);
		Customer orderBy = customerService.get(clientId);
		order.setCustomer(orderBy);
		
		List<OrderStatusEntry> statuses = new ArrayList<OrderStatusEntry>();
		OrderStatusEntry entry = new OrderStatusEntry();
		entry.setStatus(OrderStatus.CREATED);
		entry.setTime(new Date());
		statuses.add(entry);
		order.setStatuses(statuses);
		
		Date orderTime = new Date();
		order.setOrderTime(orderTime);
		
		Order created = orderService.upsert(order);
		Assert.notNull(created);
		
		exec.setVariable("orderId", created.getId());
		logger.info("create an order successfully");

	}

}
