package com.meishi.service;

import java.util.ArrayList;
import java.util.Collections;
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
		Administrator admin = get(identity);
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
	public Administrator selectByStatusLocationRank(Point location, Distance distance) {
		List<Administrator> statusSet = getAllAvailable();
		List<Administrator> locationSet = adminRepo.findByLocationNear(location, distance);
		List<Administrator> joinSet = join(statusSet, locationSet);
		if (joinSet == null || joinSet.size() == 0) {
			throw new RuntimeException("Can not find correct admin based on: " + location + " " + distance);
		}

		Collections.sort(joinSet);
		Collections.reverse(joinSet);

		// return highest rank
		return joinSet.get(0);
	}

	private List<Administrator> join(List<Administrator> statusSet, List<Administrator> locationSet) {
		List<Administrator> result = new ArrayList<Administrator>();
		if (statusSet == null || statusSet.size() == 0) {
			return null;
		}
		if (locationSet == null || locationSet.size() == 0) {
			return null;
		}
		for (Administrator admin : statusSet) {
			if (locationSet.contains(admin))
			result.add(admin);
		}
		return result;
	}

	@Override
	public Administrator getByWorker(Worker worker) {
		return adminRepo.findByWorker(worker);
	}

	@Override
	public Administrator getByWorker(String identity) {
		return adminRepo.findByWorker(identity);
	}

}
