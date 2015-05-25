package com.meishi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.util.Assert;

import com.meishi.model.Constants;
import com.meishi.model.Worker;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.WorkerRepository;

public class WorkerFinderImpl<T extends Worker> implements WorkerFinder<T> {

	private WorkerRepository<T> workerRepo;

	public WorkerFinderImpl(WorkerRepository<T> repo) {
		Assert.notNull(repo);
		this.workerRepo = repo;
	}

	@Override
	public T findWorker(Point location) {
		return findWorker(location, Constants.DEFAULT_DISTANCE);
	}

	private List<T> join(List<T> statusSet, List<T> locationSet) {
		List<T> result = new ArrayList<T>();
		if (statusSet == null || statusSet.size() == 0) {
			return null;
		}
		if (locationSet == null || locationSet.size() == 0) {
			return null;
		}
		for (T admin : statusSet) {
			if (locationSet.contains(admin))
				result.add(admin);
		}
		return result;
	}

	@Override
	public T findWorker(Point location, Distance distance) {
		List<T> statusSet = workerRepo.findByStatus(WorkerStatus.READY);
		List<T> locationSet = workerRepo.findByLocationNear(location, distance);
		List<T> joinSet = join(statusSet, locationSet);
		if (joinSet == null || joinSet.size() == 0) {
			throw new RuntimeException("Can not find correct admin based on: " + location + " " + distance);
		}

		Collections.sort(joinSet);
		Collections.reverse(joinSet);

		// return highest rank
		return joinSet.get(0);
	}

}
