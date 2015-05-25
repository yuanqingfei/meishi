package com.meishi.repository;

import java.util.List;

import com.meishi.model.Order;

public interface OrderRepositoryCustom {
	
	public List<Order> findByCook(String cookIdentity);
	
	public List<Order> findBySender(String senderIdentity);

}
