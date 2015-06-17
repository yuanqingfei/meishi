package com.meishi.repository;

import java.util.List;

import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.meishi.model.Rank;
import com.meishi.model.Worker;
import com.meishi.model.WorkerStatus;

public interface WorkerRepository<T extends Worker> {

	List<T> findByStatus(@Param("status") WorkerStatus status);

	List<T> findByRank(@Param("rank") Rank rank);

	@RestResource(exported = true)
	List<T> findByLocationNear(@Param("location") Point location, @Param("distance") Distance distance);

	T findByIdentity(@Param("identity") String identity);
	
	@RestResource(exported = false)
	List<T> findByLocationWithin(Circle c);

	@RestResource(exported = false)
    List<T> findByLocationWithin(Box b);
}
