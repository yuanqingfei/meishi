package com.meishi.repository;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import com.meishi.model.Rank;
import com.meishi.model.Worker;
import com.meishi.model.WorkerStatus;

public interface WorkerRepository<T extends Worker> {

	public List<T> findByStatus(WorkerStatus status);

	public List<T> findByRank(Rank rank);

	public List<T> findByLocationNear(Point location, Distance distance);

	public T findByIdentity(String identity);
}
