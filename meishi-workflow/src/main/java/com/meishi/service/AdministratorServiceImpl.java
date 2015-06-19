package com.meishi.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;



import com.meishi.model.Administrator;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.AdminRepository;
import com.meishi.util.Constants;

@Component
public class AdministratorServiceImpl implements AdministratorService {
	
	private Logger logger = Logger.getLogger(AdministratorServiceImpl.class.getSimpleName());

	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private UserAndGroupService ugService;

	private WorkerFinder<Administrator> workerFinder;

	@PostConstruct
	public void setUpRepo() {
		workerFinder = new WorkerFinderImpl<Administrator>(adminRepo);
	}

	@Override
	public Administrator upsert(Administrator entity) {
		Administrator admin = adminRepo.save(entity);
		ugService.createUser(entity.getIdentity(), entity.getPassword(), Constants.ADMIN_GROUP_ID);
		return admin;
	}

	@Override
	public void delete(String identity) {
		ugService.deleteUser(identity, Constants.ADMIN_GROUP_ID);
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
		ugService.deleteAll(Constants.ADMIN_GROUP_ID);
		adminRepo.deleteAll();
	}

	@Override
	public Administrator findByLocation(Point location, Distance distance) {
		List<Administrator> admins = adminRepo.findByLocationNear(location, distance);
		if (admins == null || admins.size() == 0) {
			logger.warn("there is no admin around " + location + " within " + distance);
		}
		
		// use random admin for now
		return getAll().get(0);
	}

}
