package com.meishi.repository;

import java.util.List;

import com.meishi.model.Cook;
import com.meishi.model.Dish;

public interface CookRepositoryCustom {

	public List<Cook> findByDish(Dish dish);

	public List<Cook> findByDish(String dishName);

}
