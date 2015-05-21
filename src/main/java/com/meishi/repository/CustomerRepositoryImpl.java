package com.meishi.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;

import com.meishi.model.Cook;
import com.meishi.model.Dish;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private MongoOperations operation;
	
	@Autowired
	private CookRepository cookRepo;
	
	private static final Double DEFAULT_DISTANCE = 2.0; // kilometer

	@Override
	public List<Dish> recommendDishes(Point location) {
		List<Cook> cooks = cookRepo.findByLocationNear(location, new Distance(DEFAULT_DISTANCE));
		if(cooks == null || cooks.size() == 0){
			logger.warn("there is no dishes around " + location + " within " + DEFAULT_DISTANCE);
		}
		List<Dish> result = new ArrayList<Dish>();
		for(Cook cook : cooks){
			result.addAll(cook.getDishes());
		}
		return result;
	}

}
