package com.meishi.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;

import com.meishi.model.Administrator;
import com.meishi.model.Worker;

public class AdminRepositoryImpl implements AdminRepositoryCustom {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private MongoOperations operations;

	/**
	 * db.administrator.find({workers : {$all :[{$elemMatch : {identity :
	 * '999999999'}}]}});
	 * 
	 * db.administrator.find({workers : {$elemMatch : {identity :
	 * '999999999'}}});
	 */
	@Override
	public Administrator findByWorker(Worker worker) {
		return findByWorker(worker.getIdentity());
	}

	@Override
	public Administrator findByWorker(String workerIdentity) {
		Query query = new Query(Criteria.where("workers").elemMatch(Criteria.where("identity").is(workerIdentity)));
		List<Administrator> result = operations.find(query, Administrator.class);
		if (result.size() == 1) {
			return result.get(0);
		}
		if (result.size() > 1) {
			String errorMessage = "worker:  " + workerIdentity + "can only belog to one admin";
			logger.error(errorMessage);
			throw new RuntimeException(errorMessage);
		}

		return null;
	}

}
