package com.meishi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.meishi.model.Administrator;

@RepositoryRestResource(path = "admins")
public interface AdminRepository extends MongoRepository<Administrator, String>, WorkerRepository<Administrator>,
		AdminRepositoryCustom {

}
