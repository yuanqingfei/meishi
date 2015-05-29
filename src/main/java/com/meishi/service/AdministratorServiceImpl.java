package com.meishi.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.meishi.model.Administrator;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.AdminRepository;

@Component
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	private AdminRepository adminRepo;

	private WorkerFinder<Administrator> workerFinder;

	@PostConstruct
	public void setUpRepo() {
		workerFinder = new WorkerFinderImpl<Administrator>(adminRepo);
	}

	@Override
	public Administrator upsert(Administrator entity) {
		return adminRepo.save(entity);
	}

	@Override
	public void delete(String identity) {
		Administrator admin = adminRepo.findByIdentity(identity);
		adminRepo.delete(admin);
	}

	@Override
	public Boolean isExisted(String identity) {
		Administrator admin = adminRepo.findByIdentity(identity);
		return adminRepo.exists(admin.getId());
	}

	@Override
	public List<Administrator> getAll() {
		return adminRepo.findAll();
	}

	@Override
	public void disable(String identity) {
		Administrator admin = adminRepo.findByIdentity(identity);
		admin.setStatus(WorkerStatus.DISABLE);
		adminRepo.save(admin);
	}

	@Override
	public Long count() {
		return adminRepo.count();
	}

	@Override
	public Administrator get(String identity) {
		return adminRepo.findByIdentity(identity);
	}

	@Override
	public Administrator getByWorker(String identity) {
		return adminRepo.findByWorker(identity);
	}

	@Override
	public void deleteAll() {
		adminRepo.deleteAll();
	}

	@Override
	public Administrator findByLocation(Point location, Distance distance) {
		List<Administrator> admins = adminRepo.findByLocationNear(location, distance);
		if (admins == null || admins.size() == 0) {
			throw new RuntimeException("there is no admin around " + location + " within " + distance);
		}
		return admins.get(0);
	}

}
