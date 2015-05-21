package com.meishi.service;

import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.meishi.model.Customer;
import com.meishi.model.Dish;

@Component
public class CustomerServiceImpl implements CustomerService {

	@Override
	public Customer upsert(Customer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String identity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Customer get(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isExisted(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getHighestValueCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dish> recommendDishes(Point location) {
		// TODO Auto-generated method stub
		return null;
	}

}
