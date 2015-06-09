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

@Component
public class UserAndGroupInitializer {

	@Autowired
	private IdentityService identityService;

	@Autowired
	private AdminRepository adminService;

	@Autowired
	private CookRepository cookService;

	@Autowired
	private SenderRepository senderService;

	@Autowired
	private CustomerRepository customerService;

	private static final String ADMIN_GROUP_ID = "admin";
	private static final String COOK_GROUP_ID = "cook";
	private static final String SENDER_GROUP_ID = "sender";
	private static final String CLIENT_GROUP_ID = "client";

	@PostConstruct
	public void setUp() {
		addToIdentity(adminService.findAll(), createGroup(ADMIN_GROUP_ID));
		addToIdentity(customerService.findAll(), createGroup(CLIENT_GROUP_ID));
		addToIdentity(cookService.findAll(), createGroup(COOK_GROUP_ID));
		addToIdentity(senderService.findAll(), createGroup(SENDER_GROUP_ID));
	}

	private void addToIdentity(List<? extends Person> persons, Group group) {
		for (Person person : persons) {
			createUser(person.getIdentity(), person.getPassword(), group);
		}
	}

	private User createUser(String identity, String password, Group group) {
		List<User> users = identityService.createUserQuery().userId(identity).list();
		if (users.size() == 1) {
			User user = users.get(0);
			if (user.getPassword().equals(password)) {
				return user;
			} else {
				identityService.deleteUser(identity);
				return createUserDirectly(identity, password, group);
			}
		} else if (users.size() == 0) {
			return createUserDirectly(identity, password, group);
		} else {
			throw new RuntimeException("there are more than 1 users: " + users);
		}

	}

	private User createUserDirectly(String identity, String password, Group group) {
		User user = identityService.newUser(identity);
		user.setPassword(password);
		identityService.saveUser(user);
		identityService.createMembership(user.getId(), group.getId());
		return user;
	}

	private Group createGroup(String groupId) {
		List<Group> groups = identityService.createGroupQuery().groupId(groupId).list();
		if (groups.size() == 1) {
			return groups.get(0);
		} else if (groups.size() == 0) {
			Group group = identityService.newGroup(groupId);
			identityService.saveGroup(group);
			return group;
		} else {
			throw new RuntimeException("group number more than 1: " + groups);
		}
	}

}
