package com.meishi.repository;

import java.util.List;

import org.springframework.data.geo.Point;

import com.meishi.model.Dish;

public interface CustomerRepositoryCustom {
	
	public List<Dish> recommendDishes(Point location);
	
}
