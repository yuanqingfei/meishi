package com.meishi.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;

import com.meishi.model.Cook;
import com.meishi.model.Dish;
import com.meishi.util.Constants;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private DishRepository dishRepo;

	@Autowired
	private CookRepository cookRepo;

	@Override
	public List<Dish> recommendDishes(Point location) {
		List<Cook> cooks = cookRepo.findByLocationNear(location, Constants.DEFAULT_DISTANCE);
		if (cooks == null || cooks.size() == 0) {
			logger.warn("there is no dishes around " + location + " within " + Constants.DEFAULT_DISTANCE);
		}
		List<Dish> result = new ArrayList<Dish>();
		for (Cook cook : cooks) {
			for (String dishId : cook.getDishIds()) {
				result.add(dishRepo.findOne(dishId));
			}
		}
		return result;
	}

}
