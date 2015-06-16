package com.meishi.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.meishi.model.Cook;
import com.meishi.model.Rank;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.CookRepository;
import com.meishi.util.Constants;

@Component
public class CookServiceImpl implements CookService {

	@Autowired
	private CookRepository cookRepo;

	private WorkerFinder<Cook> workerFinder;
	
	@Autowired
	private UserAndGroupService ugService;

	@PostConstruct
	public void setUpRepo() {
		workerFinder = new WorkerFinderImpl<Cook>(cookRepo);
	}

	@Override
	public Cook upsert(Cook entity) {
		Cook cook = cookRepo.save(entity);
		ugService.createUser(entity.getIdentity(), entity.getPassword(), Constants.COOK_GROUP_ID);
		return cook;
	}

	@Override
	public void delete(String identity) {
		ugService.deleteUser(identity, Constants.COOK_GROUP_ID);
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
		return workerFinder.findWorker(location, distance);
	}

	@Override
	public List<Cook> getByDish(String dishName) {
		return cookRepo.findByDish(dishName);
	}

	@Override
	public void deleteAll() {
		ugService.deleteAll(Constants.COOK_GROUP_ID);
		cookRepo.deleteAll();
	}

}
