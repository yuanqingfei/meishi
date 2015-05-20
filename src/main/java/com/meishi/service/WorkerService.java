package com.meishi.service;

import java.util.List;

import com.meishi.model.Location;
import com.meishi.model.WorkerStatus;

public interface WorkerService<T> extends CrudInterface<T> {

	public abstract List<T> getAll(WorkerStatus status);

	public abstract List<T> getAllAvailable();

	public abstract T getNearest(Location location);

	public abstract List<T> getRankHighest();

	public abstract void disable(String identity);

}
