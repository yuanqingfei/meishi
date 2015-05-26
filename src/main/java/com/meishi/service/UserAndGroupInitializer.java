package com.meishi.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meishi.model.Person;

@Component
public class UserAndGroupInitializer {

	@Autowired
	private IdentityService identityService;

	@Autowired
	private AdministratorService adminService;

	@Autowired
	private CookService cookService;

	@Autowired
	private SenderService senderService;

	@Autowired
	private CustomerService customerService;

	private void addToIdentity(List<? extends Person> persons, Group group) {
		for (Person person : persons) {
			User user = identityService.newUser(person.getIdentity());
			user.setPassword(person.getPassword());
			identityService.saveUser(user);
			identityService.createMembership(user.getId(), group.getId());
		}
	}

	@PostConstruct
	public void setUp() {
		Group group = identityService.newGroup("admin");
		group.setName("admin");
		identityService.saveGroup(group);
		addToIdentity(adminService.getAll(), group);

		group = identityService.newGroup("client");
		group.setName("client");
		identityService.saveGroup(group);
		addToIdentity(customerService.getAll(), group);

		group = identityService.newGroup("cook");
		group.setName("cook");
		identityService.saveGroup(group);
		addToIdentity(cookService.getAll(), group);

		group = identityService.newGroup("sender");
		group.setName("sender");
		identityService.saveGroup(group);
		addToIdentity(senderService.getAll(), group);
	}

}
