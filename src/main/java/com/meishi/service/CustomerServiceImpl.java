package com.meishi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.meishi.model.Customer;
import com.meishi.model.Dish;
import com.meishi.repository.CustomerRepository;

@Component
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public void deleteAll() {
		customerRepo.deleteAll();
	}

	@Override
	public Customer upsert(Customer entity) {
		return customerRepo.save(entity);
	}

	@Override
	public void delete(String identity) {
		customerRepo.delete(get(identity));

	}

	@Override
	public Customer get(String identity) {
		return customerRepo.findByIdentity(identity);
	}

	@Override
	public Boolean isExisted(String identity) {
		return customerRepo.exists(get(identity).getId());
	}

	@Override
	public Long count() {
		return customerRepo.count();
	}

	@Override
	public Customer getHighestValueCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAll() {
		return customerRepo.findAll();
	}

	@Override
	public List<Dish> recommendDishes(Point location) {
		return customerRepo.recommendDishes(location);
	}

}
