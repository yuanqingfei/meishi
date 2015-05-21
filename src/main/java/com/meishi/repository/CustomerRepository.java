package com.meishi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meishi.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>, CustomerRepositoryCustom {

}
