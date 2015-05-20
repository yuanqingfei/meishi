package com.meishi.service;

import java.util.List;

import com.meishi.model.Customer;

public interface CustomerService extends CrudInterface<Customer>{
	
	public abstract List<Customer> getCustomers();
	
	public abstract Customer getHighestValueCustomer();

}
