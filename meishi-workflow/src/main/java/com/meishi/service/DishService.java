package com.meishi.service;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import com.meishi.model.Dish;

public interface DishService extends Crud<Dish> {
	
	Dish getDishesByName(String name);
	
	List<Dish> getDishesByCenterAndDistance(Point center, Distance distance);

}
