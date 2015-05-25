package com.meishi.service;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

public interface WorkerFinder<T> {

	T findWorker(Point location);

	T findWorker(Point location, Distance distance);

}
