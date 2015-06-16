package com.meishi.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.meishi.model.Customer;
import com.meishi.util.Constants;

@Component
@RepositoryEventHandler(Customer.class)
public class CustomerEventHandler {

	Logger logger = Logger.getLogger(CustomerEventHandler.class);

	@Autowired
	private UserAndGroupService ugService;

	@HandleAfterCreate
	public void addCustomerToIdentity(Customer customer) {
		ugService.createUser(customer.getIdentity(), customer.getPassword(),
				Constants.CLIENT_GROUP_ID);

		logger.info("complete add customer to Activiti system: "
				+ customer.getIdentity());

	}

}
