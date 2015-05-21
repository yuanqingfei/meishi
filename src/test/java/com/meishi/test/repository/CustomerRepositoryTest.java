package com.meishi.test.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.geo.Point;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meishi.MeishiRepositoryApplication;
import com.meishi.model.Cook;
import com.meishi.model.Customer;
import com.meishi.model.Dish;
import com.meishi.repository.CookRepository;
import com.meishi.repository.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { MeishiRepositoryApplication.class })
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private CookRepository cookRepo;

	private Cook cook;

	private Cook cook2;

	private Customer customer;

	private Dish dish;

	private Dish dish2;

	@Before
	public void setUp() {
		customer = new Customer();
		customer.setIdentity("8888888");
		double[] point = new double[] { 5, 5 };
		customer.setLocation(point);
		customerRepo.save(customer);

		cook = new Cook();
		cook.setIdentity("111");
		double[] point1 = new double[] { 6, 6 };
		cook.setLocation(point1);
		dish = new Dish();
		dish.setName("LaZiJi");
		List<Dish> dishes = new ArrayList<Dish>();
		dishes.add(dish);
		cook.setDishes(dishes);
		cookRepo.save(cook);

		cook2 = new Cook();
		cook2.setIdentity("222");
		double[] point2 = new double[] { 7, 7 };
		cook2.setLocation(point2);
		dish2 = new Dish();
		dish2.setName("HuLaTang");
		List<Dish> dishes2 = new ArrayList<Dish>();
		dishes2.add(dish2);
		cook2.setDishes(dishes2);
		cookRepo.save(cook2);
	}

	@After
	public void tearDown() {
		cookRepo.delete(cook);
		cookRepo.delete(cook2);
		customerRepo.delete(customer);
	}

	@Test
	public void testRecommondDishes() {
		double[] loc = customer.getLocation();
		Point point = new Point(loc[0], loc[1]);
		List<Dish> dishes = customerRepo.recommendDishes(point);
		Assert.assertEquals(1, dishes.size());
		Assert.assertEquals("LaZiJi", dishes.get(0).getName());
	}

}
