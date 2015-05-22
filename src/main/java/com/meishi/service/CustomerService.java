package com.meishi.service;

import java.util.List;

import org.springframework.data.geo.Point;

import com.meishi.model.Customer;
import com.meishi.model.Dish;

public interface CustomerService extends Crud<Customer> {

	public Customer getHighestValueCustomer();

	public List<Dish> recommendDishes(Point location);

}
