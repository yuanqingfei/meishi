package com.meishi.repository;

import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.data.rest.core.annotation.RestResource;

import com.meishi.model.Dish;

public interface CustomerRepositoryCustom {
	
	@RestResource(exported = false)
	public List<Dish> recommendDishes(Point location);
	
}
