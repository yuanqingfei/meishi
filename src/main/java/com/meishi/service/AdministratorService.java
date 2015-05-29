package com.meishi.service;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import com.meishi.model.Administrator;

public interface AdministratorService extends Crud<Administrator> {

	public void disable(String identity);

	public Administrator findByLocation(Point location, Distance distance);

	public Administrator getByWorker(String identity);

}
