package com.meishi.workflow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meishi.workflow.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
