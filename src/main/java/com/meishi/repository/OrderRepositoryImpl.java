package com.meishi.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.meishi.model.Cook;
import com.meishi.model.Order;
import com.meishi.model.Sender;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

	@Autowired
	private MongoOperations operations;

	@Override
	public List<Order> findOrdersByCook(Cook cook) {
		return findOrdersByCook(cook.getIdentity());
	}

	@Override
	public List<Order> findOrdersByCook(String cookIdentity) {
		Query query = new Query(Criteria.where("cooks").elemMatch(Criteria.where("identity").is(cookIdentity)));
		return operations.find(query, Order.class);
	}

	@Override
	public List<Order> findOrdersBySender(Sender sender) {
		return findOrdersBySender(sender.getIdentity());
	}

	@Override
	public List<Order> findOrdersBySender(String senderIdentity) {
		Query query = new Query(Criteria.where("senders").elemMatch(Criteria.where("identity").is(senderIdentity)));
		return operations.find(query, Order.class);
	}

}
