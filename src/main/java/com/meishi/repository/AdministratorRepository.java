package com.meishi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meishi.model.Administrator;

public interface AdministratorRepository extends MongoRepository<Administrator, String>, WorkerInterface<Administrator> {

}
