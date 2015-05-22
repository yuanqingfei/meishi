package com.meishi.service;

import com.meishi.model.Dish;

public interface DishService extends Crud<Dish> {
	
	Dish getDishesByName(String name);

}
