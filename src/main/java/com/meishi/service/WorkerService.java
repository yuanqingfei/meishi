package com.meishi.service;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import com.meishi.model.Worker;
import com.meishi.model.WorkerStatus;

public interface WorkerService<T extends Worker> extends Crud<T> {

	public List<T> getAll(WorkerStatus status);

	public List<T> getAllAvailable();
	
	public T selectByStatusLocationRank(Point location, Distance distance);

	public List<T> getRankHighest();
	
	public void disable(String identity);
	
	public void occupy(String identity);
	
	public void release(String identity);

}
