package com.meishi.test.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meishi.MeishiRepositoryApplication;
import com.meishi.model.Administrator;
import com.meishi.model.Cook;
import com.meishi.model.Customer;
import com.meishi.model.Dish;
import com.meishi.model.Order;
import com.meishi.model.Sender;
import com.meishi.repository.DishRepository;
import com.meishi.repository.OrderRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { MeishiRepositoryApplication.class })
public class OrderRepositoryTest {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private DishRepository dishRepo;

	private Order order;

	private Cook cook;

	private Sender sender;

	private Administrator admin;

	private Customer customer;

	private Date orderTime = new Date();

	@Before
	public void setUp() {
		orderRepo.deleteAll();

		order = new Order();
		Dish meishi1 = new Dish();
		meishi1.setName("LaZiJiDing");
		Dish saved = dishRepo.save(meishi1);

		customer = new Customer();
		customer.setName("Customer");
		customer.setIdentity("1234567Consumer");
		order.setCustomer(customer);

		cook = new Cook();
		cook.setName("厨师张");
		cook.setIdentity("1234567Cook");
		cook.getDishIds().add(saved.getId());
		List<Cook> cooks = new ArrayList<Cook>();
		cooks.add(cook);
		order.setCooks(cooks);

		sender = new Sender();
		sender.setName("Mr. Zhang");
		sender.setIdentity("1234567Transporter");
		List<Sender> senders = new ArrayList<Sender>();
		senders.add(sender);
		order.setSenders(senders);

		admin = new Administrator();
		admin.setName("Mr. Li");
		admin.setIdentity("1234567Admin");
		order.setAdministrator(admin);

		order.setOrderTime(orderTime);

		orderRepo.save(order);
	}

	@After
	public void tearDown() {
		orderRepo.delete(orderRepo.findByAdministrator_Identity(admin.getIdentity()).get(0).getId());
	}

	@Test
	public void testFindByCustomer_Identity() {
		Assert.assertEquals(1, orderRepo.findByCustomer_Identity(customer.getIdentity()).size());
	}

	@Test
	public void testFindByAdministrator_Identity() {
		Assert.assertEquals(1, orderRepo.findByAdministrator_Identity(admin.getIdentity()).size());
	}

	@Test
	public void testFindByCustomer_IdCardAndOrderTime() {
		Assert.assertEquals("厨师张", orderRepo.findByCustomer_IdentityAndOrderTime(customer.getIdentity(), orderTime)
				.getCooks().get(0).getName());
	}

	@Test
	public void testFindOrdersByCook() {
		Assert.assertNotNull(orderRepo.findOrdersByCook(cook));
	}
	
	@Test
	public void testFindOrdersByCookIdentity() {
		Assert.assertNotNull(orderRepo.findOrdersByCook(cook.getIdentity()));
	}
	
	@Test
	public void testFindOrdersBySender() {
		Assert.assertNotNull(orderRepo.findOrdersBySender(sender));
	}
	
	@Test
	public void testFindOrdersBySenderIdentity() {
		Assert.assertNotNull(orderRepo.findOrdersBySender(sender.getIdentity()));
	}

}
