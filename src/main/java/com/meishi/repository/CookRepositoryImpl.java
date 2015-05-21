package com.meishi.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.meishi.model.Cook;
import com.meishi.model.Dish;

public class CookRepositoryImpl implements CookRepositoryCustom {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private MongoOperations operations;

	@Override
	public List<Cook> findByDish(Dish dish) {
		return findByDish(dish.getName());
	}

	@Override
	public List<Cook> findByDish(String dishName) {
		Query query = new Query(Criteria.where("dishes").elemMatch(Criteria.where("name").is(dishName)));
		List<Cook> result = operations.find(query, Cook.class);

		if (result.size() == 0) {
			String errorMessage = "This dish " + dishName + " has no cook, but exposed to customer";
			logger.error(errorMessage);
			throw new RuntimeException(errorMessage);
		}

		return result;
	}

}
