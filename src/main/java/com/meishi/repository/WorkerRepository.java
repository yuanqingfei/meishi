package com.meishi.repository;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.meishi.model.Rank;
import com.meishi.model.Worker;
import com.meishi.model.WorkerStatus;

public interface WorkerRepository<T extends Worker> {

	public List<T> findByStatus(@Param("status") WorkerStatus status);

	public List<T> findByRank(@Param("rank") Rank rank);

	@RestResource(exported = false)
	public List<T> findByLocationNear(Point location, Distance distance);

	public T findByIdentity(@Param("identity") String identity);

}
