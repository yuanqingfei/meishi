package com.meishi.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meishi.model.Dish;
import com.meishi.repository.DishRepository;

@Component
public class DishServiceImpl implements DishService {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private DishRepository dishRepo;

	@Override
	public Dish upsert(Dish entity) {
		return dishRepo.save(entity);
	}

	@Override
	public void delete(String dishName) {
		dishRepo.delete(dishRepo.findByName(dishName));
		;

	}

	@Override
	public Dish get(String dishName) {
		return dishRepo.findByName(dishName);
	}

	@Override
	public Boolean isExisted(String dishName) {
		Dish dish = dishRepo.findByName(dishName);
		return dish == null ? false : true;
	}

	@Override
	public Long count() {
		return dishRepo.count();
	}

	@Override
	public List<Dish> getAll() {
		return dishRepo.findAll();
	}

	@Override
	public Dish getDishesByName(String name) {
		return dishRepo.findByName(name);
	}

	@Override
	public void deleteAll() {
		dishRepo.deleteAll();
	}

}
