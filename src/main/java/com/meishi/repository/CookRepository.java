package com.meishi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meishi.model.Cook;

public interface CookRepository extends MongoRepository<Cook, String>, WorkerInterface<Cook> {

}
