package com.meishi.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meishi.MeishiApplication;
import com.meishi.model.Administrator;
import com.meishi.model.Cook;
import com.meishi.model.Customer;
import com.meishi.model.Dish;
import com.meishi.model.Sender;
import com.meishi.service.AdministratorService;
import com.meishi.service.CookService;
import com.meishi.service.CustomerService;
import com.meishi.service.DishService;
import com.meishi.service.SenderService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { MeishiApplication.class })
public class ServiceIntegrationTest {

	@Autowired
	private AdministratorService adminService;

	@Autowired
	private CookService cookService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private DishService dishService;

	@Autowired
	private SenderService senderService;

	/**
	 * try to setup all Master Data(static data) for future test
	 */
	@Before
	public void setUp() {
		adminService.deleteAll();
		cookService.deleteAll();
		customerService.deleteAll();
		dishService.deleteAll();
		senderService.deleteAll();
		
		Administrator admin = new Administrator();
		admin.setName("admin");
		admin.setIdentity("123456");
		admin.setPassword("111");
		admin.setLocation(new double[]{5, 5});
		
		Cook cook = new Cook();
		cook.setName("cook");
		cook.setIdentity("1234567");
		cook.setPassword("111");
		cook.setLocation(new double[]{5, 4});
		
		Sender sender = new Sender();
		sender.setName("sender");
		sender.setIdentity("12345678");
		sender.setPassword("111");
		sender.setLocation(new double[]{5, 4});
		
		Customer customer = new Customer();
		customer.setName("customer");
		customer.setPassword("111");
		customer.setLocation(new double[]{5, 5});
		customer.setIdentity("123456789");;
		
		Dish dish1 = new Dish();
		dish1.setName("Dish1");
		dish1.setPrice(12.5);
		
		Dish dish2 = new Dish();
		dish2.setName("Dish2");
		dish2.setPrice(21.3);

		String dishId1 = dishService.upsert(dish1).getId();
		String dishId2 = dishService.upsert(dish2).getId();
		cook.getDishIds().add(dishId1);
		cook.getDishIds().add(dishId2);
		
		String cookId = cookService.upsert(cook).getId();
		String senderId = senderService.upsert(sender).getId();
		
		admin.getDirectWorkerIds().add(cookId);
		admin.getDirectWorkerIds().add(senderId);
		adminService.upsert(admin);
		
		customerService.upsert(customer);
	}

	@Test
	public void dummyTest() {
		Assert.assertEquals(2, 1 + 1);
	}

}
