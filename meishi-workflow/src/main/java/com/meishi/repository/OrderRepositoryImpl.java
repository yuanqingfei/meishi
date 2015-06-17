package com.meishi.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.meishi.model.Order;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

	@Autowired
	private MongoTemplate operations;

	@Override
	public List<Order> findByCook(String cookIdentity) {
		Query query = new Query(Criteria.where("cooks").elemMatch(Criteria.where("identity").is(cookIdentity)));
		return operations.find(query, Order.class);
	}

	@Override
	public List<Order> findBySender(String senderIdentity) {
		Query query = new Query(Criteria.where("senders").elemMatch(Criteria.where("identity").is(senderIdentity)));
		return operations.find(query, Order.class);
	}

}
