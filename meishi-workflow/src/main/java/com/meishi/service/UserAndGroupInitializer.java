package com.meishi.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meishi.model.Person;
import com.meishi.repository.AdminRepository;
import com.meishi.repository.CookRepository;
import com.meishi.repository.CustomerRepository;
import com.meishi.repository.SenderRepository;
import com.meishi.util.Constants;

@Component
public class UserAndGroupInitializer {

	@Autowired
	private UserAndGroupService ugService;

	@Autowired
	private AdminRepository adminService;

	@Autowired
	private CookRepository cookService;

	@Autowired
	private SenderRepository senderService;

	@Autowired
	private CustomerRepository customerService;

	@PostConstruct
	public void setUp() {
		addToIdentity(adminService.findAll(),
				ugService.createGroup(Constants.ADMIN_GROUP_ID));
		addToIdentity(customerService.findAll(),
				ugService.createGroup(Constants.CLIENT_GROUP_ID));
		addToIdentity(cookService.findAll(),
				ugService.createGroup(Constants.COOK_GROUP_ID));
		addToIdentity(senderService.findAll(),
				ugService.createGroup(Constants.SENDER_GROUP_ID));
	}

	private void addToIdentity(List<? extends Person> persons, Group group) {
		for (Person person : persons) {
			ugService.createUser(person.getIdentity(), person.getPassword(),
					group);
		}
	}


}
