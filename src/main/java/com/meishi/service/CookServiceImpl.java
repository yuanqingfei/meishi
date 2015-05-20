package com.meishi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meishi.model.Cook;
import com.meishi.model.Location;
import com.meishi.model.Rank;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.CookRepository;

@Component
public class CookServiceImpl implements CookService {

	@Autowired
	private CookRepository cookRepo;

	@Override
	public Cook saveAndUpdate(Cook entity) {
		return cookRepo.save(entity);
	}

	@Override
	public void delete(String identity) {
		Cook cook = find(identity);
		cookRepo.delete(cook);
	}

	@Override
	public Boolean isExisted(String identity) {
		Cook cook = find(identity);
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
	public Cook getNearest(Location location) {
		String streetName = location.getStreetName();
		double x = location.getCoordinationX();
		double y = location.getCoordinationY();
		List<Cook> cooks = getAllAvailable();
		double shortestDistance = 100;
		Cook selected = null;
		for (Cook cook : cooks) {
			Location cookLocation = cook.getAddress();
			String cookStreetName = cookLocation.getStreetName();
			double cookX = cookLocation.getCoordinationX();
			double cookY = cookLocation.getCoordinationY();
			if (cookStreetName != null && cookStreetName.equalsIgnoreCase(streetName)) {
				return cook;
			}
			double currentDistance = Math.sqrt((cookX - x) * (cookX - x) + (cookY - y) * (cookY - y));
			if (currentDistance < shortestDistance) {
				shortestDistance = currentDistance;
				selected = cook;
			}
		}
		return selected;
	}

	@Override
	public List<Cook> getRankHighest() {
		return cookRepo.findByRank(Rank.Rank5);
	}

	@Override
	public void disable(String identity) {
		Cook cook = find(identity);
		cook.setStatus(WorkerStatus.DISABLE);
		cookRepo.save(cook);
	}

	@Override
	public Long count() {
		return cookRepo.count();
	}

	@Override
	public Cook find(String identity) {
		return cookRepo.findByIdentity(identity);
	}
}
