package com.meishi.workflow.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meishi.workflow.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

	public List<Order> findByCustomer_Identity(String identity);

	public List<Order> findByAdministrator_Identity(String identity);

	public List<Order> findByCook_Identity(String identity);

	public List<Order> findByTransporter_Identity(String identity);
	
	public Order findByCustomer_IdentityAndOrderTime(String identity, Date orderTime);
}
