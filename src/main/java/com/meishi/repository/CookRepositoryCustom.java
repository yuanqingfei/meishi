package com.meishi.repository;

import java.util.List;

import com.meishi.model.Cook;

public interface CookRepositoryCustom {

	public List<Cook> findByDish(String dishName);

}
