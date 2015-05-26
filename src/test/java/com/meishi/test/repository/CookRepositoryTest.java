package com.meishi.test.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meishi.model.Cook;
import com.meishi.model.Dish;
import com.meishi.model.Rank;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.CookRepository;
import com.meishi.repository.DishRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { MeishiRepositoryApplicationForTest.class })
public class CookRepositoryTest {

	@Autowired
	private CookRepository cookRepo;

	@Autowired
	private DishRepository dishRepo;

	private Cook cook;

	private Dish dish;

	@Before
	public void setUp() {
		cookRepo.deleteAll();

		cook = new Cook();
		cook.setIdentity("8888888");
		double[] point = new double[] { 5, 6 };
		cook.setLocation(point);
		cook.setStatus(WorkerStatus.READY);
		cook.setRank(Rank.Rank5);

		dish = new Dish();
		dish.setName("LaZiJi");
		Dish existed2 = dishRepo.save(dish);
		cook.getDishIds().add(existed2.getId());

		cookRepo.save(cook);
	}

	@After
	public void tearDown() {
		cookRepo.deleteAll();
	}

	@Test
	public void testFindByDishName() {
		Assert.assertNotNull(cookRepo.findByDish(dish.getName()));
	}

}
