package com.meishi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.meishi.model.Administrator;
import com.meishi.model.Rank;
import com.meishi.model.Worker;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.AdminRepository;

@Component
public class AdministratorServiceImpl implements AdministratorService {

	private WorkerFinder workerFinder;

	@Autowired
	private AdminRepository adminRepo;

	@Override
	public Administrator upsert(Administrator entity) {
		return adminRepo.save(entity);
	}

	@Override
	public void delete(String identity) {
		Administrator admin = get(identity);
		adminRepo.delete(admin);
	}

	@Override
	public Boolean isExisted(String identity) {
		Administrator admin = get(identity);
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
	public List<Administrator> getRankHighest() {
		return adminRepo.findByRank(Rank.Rank5);
	}

	@Override
	public void disable(String identity) {
		Administrator admin = adminRepo.findByIdentity(identity);
		admin.setStatus(WorkerStatus.DISABLE);
		adminRepo.save(admin);
	}

	@Override
	public void occupy(String identity) {
		Administrator entity = adminRepo.findByIdentity(identity);
		entity.setStatus(WorkerStatus.BUSY);
		adminRepo.save(entity);
	}

	@Override
	public void release(String identity) {
		Administrator entity = adminRepo.findByIdentity(identity);
		entity.setStatus(WorkerStatus.READY);
		adminRepo.save(entity);
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
	public Administrator selectByStatusLocationRank(Point location, Distance distance) {
		workerFinder = new WorkerFinderImpl(adminRepo);
		return (Administrator) workerFinder.findWorker(location, distance);
	}

	@Override
	public Administrator getByWorker(Worker worker) {
		return adminRepo.findByWorker(worker);
	}

	@Override
	public Administrator getByWorker(String identity) {
		return adminRepo.findByWorker(identity);
	}

	@Override
	public void deleteAll() {
		adminRepo.deleteAll();
	}

}
