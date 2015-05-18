package com.meishi.test;

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

import com.meishi.workflow.model.Administrator;
import com.meishi.workflow.model.Cook;
import com.meishi.workflow.model.Customer;
import com.meishi.workflow.model.Meishi;
import com.meishi.workflow.model.Order;
import com.meishi.workflow.model.Transporter;
import com.meishi.workflow.repository.MongoApplication;
import com.meishi.workflow.repository.OrderRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { MongoApplication.class })
public class OrderRepositoryTest {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private OrderRepository orderRepo;

	private Date orderTime = new Date();

	@Before
	public void setUp() {
		Assert.assertNotNull(orderRepo);

		Order testOrder = new Order();
		List<Meishi> foods = new ArrayList<Meishi>();
		Meishi meishi1 = new Meishi();
		meishi1.setName("LaZiJiDing");
		foods.add(meishi1);
		testOrder.setFoods(foods);
		Customer orderBy = new Customer();
		orderBy.setName("Customer");
		orderBy.setIdentity("1234567Consumer");
		testOrder.setCustomer(orderBy);
		Cook cookBy = new Cook();
		cookBy.setName("厨师张");
		cookBy.setIdentity("1234567Cook");
		testOrder.setCook(cookBy);
		Transporter sendBy = new Transporter();
		sendBy.setName("Transporter");
		sendBy.setIdentity("1234567Transporter");
		testOrder.setTransporter(sendBy);
		Administrator admin = new Administrator();
		admin.setName("Admin");
		admin.setIdentity("1234567Admin");
		testOrder.setAdministrator(admin);

		testOrder.setOrderTime(orderTime);

		orderRepo.save(testOrder);
	}

	@Test
	public void testGetAll() {
		Assert.assertEquals(1, orderRepo.findAll().size());
	}

	@Test
	public void testFindByCustomer_IdCard() {
		Assert.assertEquals(1, orderRepo.findByCustomer_Identity("1234567Consumer").size());
	}

	@Test
	public void testFindByCook_IdCard() {
		Assert.assertEquals(1, orderRepo.findByCook_Identity("1234567Cook").size());
	}

	@Test
	public void testFindByTransporter_IdCard() {
		Assert.assertEquals(1, orderRepo.findByCook_Identity("1234567Transporter").size());
	}

	@Test
	public void testFindByAdministrator_IdCard() {
		Assert.assertEquals(1, orderRepo.findByAdministrator_Identity("1234567Admin").size());
	}
	
	@Test
	public void testFindByCustomer_IdCardAndOrderTime(){
		Assert.assertEquals("厨师张", orderRepo.findByCustomer_IdentityAndOrderTime("1234567Consumer", orderTime).getCook().getName());
	}

	@After
	public void tearDown() {
		orderRepo.delete(orderRepo.findByAdministrator_Identity("1234567Admin").get(0).getId());
	}

}
