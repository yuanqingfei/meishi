package com.meishi.test.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meishi.MeishiRepositoryApplication;
import com.meishi.model.Cook;
import com.meishi.model.Dish;
import com.meishi.model.Rank;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.CookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { MeishiRepositoryApplication.class })
public class CookRepositoryTest {

	@Autowired
	private CookRepository cookRepo;

	private Cook cook;

	private Dish dish;

	@Before
	public void setUp() {
		cook = new Cook();
		cook.setIdentity("8888888");
		double[] point = new double[] { 5, 6 };
		cook.setLocation(point);
		cook.setStatus(WorkerStatus.READY);
		cook.setRank(Rank.Rank5);
		
		dish = new Dish();
		dish.setName("LaZiJi");
		List<Dish> dishes = new ArrayList<Dish>();
		dishes.add(dish);
		cook.setDishes(dishes);
		
		cookRepo.save(cook);
	}

	@After
	public void tearDown() {
		cookRepo.delete(cook);
	}

	@Test
	public void testFindByDish() {
		Assert.assertNotNull(cookRepo.findByDish(dish));
	}

	@Test
	public void testFindByDishName() {
		Assert.assertNotNull(cookRepo.findByDish(dish.getName()));
	}

}
