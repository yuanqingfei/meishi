package com.meishi.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.meishi.model.Cook;
import com.meishi.model.Dish;

public class CookRepositoryImpl implements CookRepositoryCustom {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private MongoTemplate operations;

	@Autowired
	private DishRepository dishRepo;

	@Override
	public List<Cook> findByDish(String dishName) {
		List<Cook> result = new ArrayList<Cook>();
		Dish dish = dishRepo.findByName(dishName);
		if(dish == null){
			String errorMessage = "We can not find this dish: " + dishName;
			logger.error(errorMessage);
			throw new RuntimeException(errorMessage);
		}
		Query query = new Query(Criteria.where("dishIds").all(dish.getId()));
		result.addAll(operations.find(query, Cook.class));

		if (result.size() == 0) {
			String errorMessage = "This dish " + dishName + " has no cook, but exposed to customer";
			logger.error(errorMessage);
			throw new RuntimeException(errorMessage);
		}

		return result;
	}

}
