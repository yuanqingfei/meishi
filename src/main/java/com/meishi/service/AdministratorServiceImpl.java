package com.meishi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meishi.model.Administrator;
import com.meishi.model.Location;
import com.meishi.model.Rank;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.AdministratorRepository;

@Component
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	private AdministratorRepository adminRepo;

	@Override
	public Administrator saveAndUpdate(Administrator entity) {
		return adminRepo.save(entity);
	}

	@Override
	public void delete(String identity) {
		Administrator admin = find(identity);
		adminRepo.delete(admin);
	}

	@Override
	public Boolean isExisted(String identity) {
		Administrator admin = find(identity);
		return adminRepo.exists(admin.getId());
	}

	@Override
	public List<Administrator> getAll() {
		return adminRepo.findAll();
	}

	@Override
	public List<Administrator> getAll(WorkerStatus status) {
		return adminRepo.findByStatus(status);
	}

	@Override
	public List<Administrator> getAllAvailable() {
		return adminRepo.findByStatus(WorkerStatus.READY);
	}

	@Override
	public Administrator getNearest(Location location) {
		String streetName = location.getStreetName();
		double x = location.getCoordinationX();
		double y = location.getCoordinationY();
		List<Administrator> admins = getAllAvailable();
		double shortestDistance = 100;
		Administrator selected = null;
		for (Administrator admin : admins) {
			Location adminLocation = admin.getAddress();
			String adminStreetName = adminLocation.getStreetName();
			double adminX = adminLocation.getCoordinationX();
			double adminY = adminLocation.getCoordinationY();
			if (adminStreetName != null && adminStreetName.equalsIgnoreCase(streetName)) {
				return admin;
			}
			double currentDistance = Math.sqrt((adminX - x) * (adminX - x) + (adminY - y) * (adminY - y));
			if (currentDistance < shortestDistance) {
				shortestDistance = currentDistance;
				selected = admin;
			}
		}
		return selected;
	}

	@Override
	public List<Administrator> getRankHighest() {
		return adminRepo.findByRank(Rank.Rank5);
	}

	@Override
	public void disable(String identity) {
		Administrator admin = find(identity);
		admin.setStatus(WorkerStatus.DISABLE);
		adminRepo.save(admin);
	}

	@Override
	public Long count() {
		return adminRepo.count();
	}

	@Override
	public Administrator find(String identity) {
		return adminRepo.findByIdentity(identity);
	}

}
