package com.meishi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meishi.model.Sender;

public interface SenderRepository extends MongoRepository<Sender, String>, WorkerRepository<Sender> {
}
