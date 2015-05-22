package com.meishi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import com.meishi.model.Constants;
import com.meishi.model.Worker;
import com.meishi.model.WorkerStatus;
import com.meishi.repository.WorkerRepository;

public class WorkerFinderImpl implements WorkerFinder {

	private WorkerRepository workerRepo;

	public WorkerFinderImpl(WorkerRepository workerRepo) {
		this.workerRepo = workerRepo;
	}

	@Override
	public Worker findWorker(Point location) {
		return findWorker(location, Constants.DEFAULT_DISTANCE);
	}

	private List join(List statusSet, List locationSet) {
		List result = new ArrayList();
		if (statusSet == null || statusSet.size() == 0) {
			return null;
		}
		if (locationSet == null || locationSet.size() == 0) {
			return null;
		}
		for (Object admin : statusSet) {
			if (locationSet.contains(admin))
				result.add(admin);
		}
		return result;
	}

	@Override
	public Worker findWorker(Point location, Distance distance) {
		List statusSet = workerRepo.findByStatus(WorkerStatus.READY);
		List locationSet = workerRepo.findByLocationNear(location, distance);
		List joinSet = join(statusSet, locationSet);
		if (joinSet == null || joinSet.size() == 0) {
			throw new RuntimeException("Can not find correct admin based on: " + location + " "
					+ Constants.DEFAULT_DISTANCE);
		}

		Collections.sort(joinSet);
		Collections.reverse(joinSet);

		// return highest rank
		return (Worker)joinSet.get(0);
	}

}
