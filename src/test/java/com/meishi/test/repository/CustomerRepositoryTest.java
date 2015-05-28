package com.meishi.test.repository;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.geo.Point;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meishi.model.Cook;
import com.meishi.model.Customer;
import com.meishi.model.Dish;
import com.meishi.repository.CookRepository;
import com.meishi.repository.CustomerRepository;
import com.meishi.repository.DishRepository;
import com.meishi.test.repository.app.MeishiRepositoryApplicationForTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { MeishiRepositoryApplicationForTest.class })
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private CookRepository cookRepo;
	
	@Autowired
	private DishRepository dishRepo;

	private Cook cook;

	private Cook cook2;

	private Customer customer;

	private Dish dish;

	private Dish dish2;

	@Before
	public void setUp() {
		customerRepo.deleteAll();
		
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
		Dish existed = dishRepo.save(dish);
		cook.getDishIds().add(existed.getId());
		cookRepo.save(cook);

		cook2 = new Cook();
		cook2.setIdentity("222");
		double[] point2 = new double[] { 7, 7 };
		cook2.setLocation(point2);
		dish2 = new Dish();
		dish2.setName("HuLaTang");
		Dish existed2 = dishRepo.save(dish2);
		cook.getDishIds().add(existed2.getId());
		cookRepo.save(cook2);
	}

	@After
	public void tearDown() {
		cookRepo.deleteAll();
		customerRepo.deleteAll();
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
