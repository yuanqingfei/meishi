package com.meishi.workflow.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meishi.model.Administrator;
import com.meishi.model.Customer;
import com.meishi.model.Dish;
import com.meishi.model.Order;
import com.meishi.model.OrderStatus;
import com.meishi.model.OrderStatusEntry;
import com.meishi.service.CustomerService;
import com.meishi.service.DishService;
import com.meishi.service.MoneyService;
import com.meishi.service.OrderService;
import com.meishi.util.Constants;

@Component
public class CreateOrderTask implements JavaDelegate {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private DishService dishService;

	@Autowired
	private MoneyService moneyService;

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		Administrator admin = (Administrator) exec.getVariable(Constants.ADMIN_VARIABLE);
		String meishiList = (String) exec.getVariable(Constants.FOOD_LIST_VARIABLE);
		String clientId = (String) exec.getVariable(Constants.CLIENT_ID_VARIABLE);

		Order order = new Order();
		order.setAdministrator(admin);

		List<Dish> foods = new ArrayList<Dish>();
		String[] meishiNames = meishiList.split(Constants.FOOD_SEPARATER_VARIABLE);
		exec.setVariable(Constants.FOOD_ARRAY_VARIABLE, meishiNames);
		Double totalPrice = 0.0;
		for (String dishName : meishiNames) {
			Dish dish = dishService.getDishesByName(dishName);
			totalPrice += dish.getPrice();
			foods.add(dish);
		}
		order.setFoods(foods);

		Customer orderBy = customerService.get(clientId);
		order.setCustomer(orderBy);

		List<OrderStatusEntry> statuses = createStatuses();
		order.setStatuses(statuses);

		Date orderTime = new Date();
		order.setOrderTime(orderTime);

		Order created = orderService.upsert(order);
		
		// after create order, we will cost customer money
		moneyService.cost(clientId, totalPrice);

		exec.setVariable(Constants.TOTAL_PRICE_VARIABLE, totalPrice);
		exec.setVariable(Constants.ORDER_ID_VARIABLE, created.getId());
		logger.info("create an order successfully");
	}

	private List<OrderStatusEntry> createStatuses() {
		List<OrderStatusEntry> statuses = new ArrayList<OrderStatusEntry>();
		OrderStatusEntry entry = new OrderStatusEntry();
		entry.setStatus(OrderStatus.CREATED);
		entry.setTime(new Date());
		statuses.add(entry);
		return statuses;
	}

}
