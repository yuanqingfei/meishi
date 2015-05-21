package com.meishi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meishi.model.Administrator;

public interface AdminRepository extends MongoRepository<Administrator, String>, WorkerRepository<Administrator>,
		AdminRepositoryCustom {

}
