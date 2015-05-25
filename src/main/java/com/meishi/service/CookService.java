package com.meishi.service;

import java.util.List;

import com.meishi.model.Cook;

public interface CookService extends WorkerService<Cook> {
	
	public List<Cook> getByDish(String dishName);

}
