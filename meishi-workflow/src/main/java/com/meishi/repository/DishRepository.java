package com.meishi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meishi.model.Dish;

/**
 *  Assume dishName will be unique
 * @author Aaron
 *
 */
public interface DishRepository extends MongoRepository<Dish, String> {

	public Dish findByName(String name);

}
