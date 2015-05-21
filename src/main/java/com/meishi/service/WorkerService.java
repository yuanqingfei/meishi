package com.meishi.service;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import com.meishi.model.Worker;
import com.meishi.model.WorkerStatus;

public interface WorkerService<T extends Worker> extends CrudInterface<T> {

	public abstract List<T> getAll(WorkerStatus status);

	public abstract List<T> getAllAvailable();

	public abstract T selectByStatusLocationRank(Point location, Distance distance);

	public abstract List<T> getRankHighest();

	public abstract void disable(String identity);

}
