package com.meishi.service;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAndGroupService {
	
	@Autowired
	private IdentityService identityService;
	
	public void deleteUser(String userId, String groupId){
		identityService.deleteMembership(userId, groupId);
		identityService.deleteUser(userId);
	}
	

	public void createUser(String identity, String password, String group) {
		createUser(identity, password, createGroup(group));
	}

	public User createUser(String identity, String password, Group group) {
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

	public User createUserDirectly(String identity, String password, Group group) {
		User user = identityService.newUser(identity);
		user.setPassword(password);
		identityService.saveUser(user);
		identityService.createMembership(user.getId(), group.getId());
		return user;
	}

	public Group createGroup(String groupId) {
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
	
	public void deleteAll(String groupId){
		List<User> users = identityService.createUserQuery().memberOfGroup(groupId).list();
		for(User user : users){
			deleteUser(user.getId(), groupId);
		}
	}

}
