package com.meishi;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import(MeishiRepositoryApplication.class)
public class MeishiWorkFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeishiWorkFlowApplication.class, args);
	}

	// @Bean
	// public DataSource database() {
	// return
	// DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/activiti?autoReconnect=true")
	// // .url("jdbc:mysql://127.0.0.1:3306/activiti?characterEncoding=UTF-8")
	// .username("root").password("").driverClassName("com.mysql.jdbc.Driver").build();
	// }

	@Bean
	public InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

		return new InitializingBean() {
			public void afterPropertiesSet() throws Exception {

				Group group = identityService.newGroup("admin");
				group.setName("admin");
				identityService.saveGroup(group);
				User admin = identityService.newUser("admin1");
				admin.setPassword("password");
				identityService.saveUser(admin);
				identityService.createMembership(admin.getId(), group.getId());

				group = identityService.newGroup("client");
				group.setName("client");
				identityService.saveGroup(group);
				User client = identityService.newUser("client1");
				client.setPassword("password");
				identityService.saveUser(client);
				identityService.createMembership(client.getId(), group.getId());

				group = identityService.newGroup("cook");
				group.setName("cook");
				identityService.saveGroup(group);
				User cook = identityService.newUser("cook1");
				cook.setPassword("password");
				identityService.saveUser(cook);
				identityService.createMembership(cook.getId(), group.getId());

				group = identityService.newGroup("sender");
				group.setName("sender");
				identityService.saveGroup(group);
				User sender = identityService.newUser("sender1");
				sender.setPassword("password");
				identityService.saveUser(sender);
				identityService.createMembership(sender.getId(), group.getId());

			}
		};
	}

}
