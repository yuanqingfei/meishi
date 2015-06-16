package com.meishi.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.meishi.model.Cook;

public interface CookRepositoryCustom {

	List<Cook> findByDish(@Param("dishName") String dishName);
	

}
