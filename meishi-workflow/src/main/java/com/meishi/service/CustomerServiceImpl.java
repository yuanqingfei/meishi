package com.meishi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.meishi.model.Customer;
import com.meishi.model.Dish;
import com.meishi.repository.CustomerRepository;
import com.meishi.util.Constants;

@Component
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private UserAndGroupService ugService;

	@Override
	public void deleteAll() {
		ugService.deleteAll(Constants.CLIENT_GROUP_ID);
		customerRepo.deleteAll();
	}

	@Override
	public Customer upsert(Customer entity) {
		Customer client = customerRepo.save(entity);
		ugService.createUser(entity.getIdentity(), entity.getPassword(), Constants.CLIENT_GROUP_ID);
		return client;
	}

	@Override
	public void delete(String identity) {
		ugService.deleteUser(identity, Constants.CLIENT_GROUP_ID);
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
