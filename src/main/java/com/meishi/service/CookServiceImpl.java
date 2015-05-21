package com.meishi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.meishi.model.Administrator;
import com.meishi.model.Cook;
import com.meishi.model.Dish;
import com.meishi.model.Rank;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.CookRepository;

@Component
public class CookServiceImpl implements CookService {

	@Autowired
	private CookRepository cookRepo;

	@Override
	public Cook upsert(Cook entity) {
		return cookRepo.save(entity);
	}

	@Override
	public void delete(String identity) {
		Cook cook = get(identity);
		cookRepo.delete(cook);
	}

	@Override
	public Boolean isExisted(String identity) {
		Cook cook = get(identity);
		return cookRepo.exists(cook.getId());
	}

	@Override
	public List<Cook> getAll() {
		return cookRepo.findAll();
	}

	@Override
	public List<Cook> getAll(WorkerStatus status) {
		return cookRepo.findByStatus(status);
	}

	@Override
	public List<Cook> getAllAvailable() {
		return cookRepo.findByStatus(WorkerStatus.READY);
	}

	@Override
	public List<Cook> getRankHighest() {
		return cookRepo.findByRank(Rank.Rank5);
	}

	@Override
	public void disable(String identity) {
		Cook cook = get(identity);
		cook.setStatus(WorkerStatus.DISABLE);
		cookRepo.save(cook);
	}

	@Override
	public Long count() {
		return cookRepo.count();
	}

	@Override
	public Cook get(String identity) {
		return cookRepo.findByIdentity(identity);
	}

	@Override
	public Cook selectByStatusLocationRank(Point location, Distance distance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cook> getByDish(Dish dish) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cook> getByDish(String dishName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void occupy(String identity) {
		Cook entity = cookRepo.findByIdentity(identity);
		entity.setStatus(WorkerStatus.BUSY);
		cookRepo.save(entity);
	}

	@Override
	public void release(String identity) {
		Cook entity = cookRepo.findByIdentity(identity);
		entity.setStatus(WorkerStatus.READY);
		cookRepo.save(entity);
	}
}
