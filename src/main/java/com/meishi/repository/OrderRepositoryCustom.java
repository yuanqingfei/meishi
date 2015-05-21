package com.meishi.repository;

import java.util.List;

import com.meishi.model.Cook;
import com.meishi.model.Order;
import com.meishi.model.Sender;

public interface OrderRepositoryCustom {
	
	public List<Order> findOrdersByCook(Cook cook);
	
	public List<Order> findOrdersByCook(String cookIdentity);
	
	public List<Order> findOrdersBySender(Sender sender);
	
	public List<Order> findOrdersBySender(String senderIdentity);

}
