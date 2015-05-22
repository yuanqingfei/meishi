package com.meishi.service;


import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import com.meishi.model.Worker;

public interface WorkerFinder {
	
	Worker findWorker(Point location);
	
	Worker findWorker(Point location, Distance distance);

}
