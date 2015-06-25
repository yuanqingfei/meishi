package com.meishi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Component;

import com.meishi.model.Cook;
import com.meishi.model.Dish;
import com.meishi.repository.DishRepository;

@Component
public class DishServiceImpl implements DishService {

	@Autowired
	private DishRepository dishRepo;
	
	@Autowired
	private MongoTemplate template;
	
	@Override
	public List<Dish> getDishesByCenterAndDistance(Point center,
			Distance distance) {
		List<Dish> result = new ArrayList<Dish>();
		
		NearQuery query = NearQuery.near(center).maxDistance(distance);
		GeoResults<Cook> cookResult = template.geoNear(query, Cook.class);
		List<GeoResult<Cook>> cooks = cookResult.getContent();
		for(GeoResult<Cook> cook : cooks){
			Distance dis = cook.getDistance();
			Cook realCook = cook.getContent();
			List<String> dishIds = realCook.getDishIds();
			for(String dishId : dishIds){
				Dish dish = dishRepo.findOne(dishId);
				dish.setDistance(dis);
				result.add(dish);
			}
		}
		
		return result;
	}


	@Override
	public Dish upsert(Dish entity) {
		return dishRepo.save(entity);
	}

	@Override
	public void delete(String dishName) {
		dishRepo.delete(dishRepo.findByName(dishName));
	}

	@Override
	public Dish get(String dishName) {
		return dishRepo.findByName(dishName);
	}

	@Override
	public Boolean isExisted(String dishName) {
		Dish dish = dishRepo.findByName(dishName);
		return dish == null ? false : true;
	}

	@Override
	public Long count() {
		return dishRepo.count();
	}

	@Override
	public List<Dish> getAll() {
		return dishRepo.findAll();
	}

	@Override
	public Dish getDishesByName(String name) {
		return dishRepo.findByName(name);
	}

	@Override
	public void deleteAll() {
		dishRepo.deleteAll();
	}

}
