package com.meishi.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.meishi.model.Order;

public interface OrderRepositoryCustom {
	
	public List<Order> findByCook(@Param("cookId") String cookIdentity);
	
	public List<Order> findBySender(@Param("senderId") String senderIdentity);

}
