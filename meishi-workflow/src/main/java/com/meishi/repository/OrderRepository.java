package com.meishi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.meishi.model.Order;

public interface OrderRepository extends MongoRepository<Order, String>, OrderRepositoryCustom {

	@RestResource(path = "findByClientId")
	public List<Order> findByCustomer_Identity(@Param("clientId") String identity);

	@RestResource(path = "findByAdminId")
	public List<Order> findByAdministrator_Identity(@Param("adminId") String identity);

	@RestResource(path = "findByClientIdAndOrderTime")
	public Order findByCustomer_IdentityAndOrderTime(@Param("clientId") String identity, @Param("orderDate") Date orderTime);
}
