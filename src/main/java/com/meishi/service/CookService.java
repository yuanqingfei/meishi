package com.meishi.service;

import java.util.List;

import com.meishi.model.Cook;
import com.meishi.model.Dish;

public interface CookService extends WorkerService<Cook> {
	
	public List<Cook> getByDish(Dish dish);

	public List<Cook> getByDish(String dishName);

}
