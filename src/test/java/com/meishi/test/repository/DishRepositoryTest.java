package com.meishi.test.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meishi.MeishiApplication.RepositoryApplication;
import com.meishi.model.Dish;
import com.meishi.repository.DishRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { RepositoryApplication.class })
public class DishRepositoryTest {

	@Autowired
	private DishRepository dishRepo;

	private Dish dish;

	@Before
	public void setUp() {
		dishRepo.deleteAll();
		dish = new Dish();
		dish.setName("HuLaTang");
		dishRepo.save(dish);
	}

	@After
	public void tearDown() {
		dishRepo.deleteAll();
	}

	@Test
	public void testFindByName() {
		Assert.assertEquals(dish.getName(), dishRepo.findByName(dish.getName()).getName());
	}
}
