package com.meishi.workflow;

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

import com.meishi.workflow.repository.MongoApplication;

//@SpringBootApplication
@Configuration
@ComponentScan("com.meishi")
@EnableAutoConfiguration
@Import(MongoApplication.class)
public class MeishiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeishiApplication.class, args);
	}

//	@Bean
//	public DataSource database() {
//		return DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/activiti?autoReconnect=true")
//		// .url("jdbc:mysql://127.0.0.1:3306/activiti?characterEncoding=UTF-8")
//				.username("root").password("").driverClassName("com.mysql.jdbc.Driver").build();
//	}

	@Bean
	public InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

		return new InitializingBean() {
			public void afterPropertiesSet() throws Exception {

				Group group = identityService.newGroup("admin");
				group.setName("admin");
				// group.setType("security-role");
				group.setType("admin-role");
				identityService.saveGroup(group);
				User admin = identityService.newUser("admin1");
				admin.setPassword("password");
				identityService.saveUser(admin);

				group = identityService.newGroup("client");
				group.setName("client");
				group.setType("client-role");
				identityService.saveGroup(group);
				User client = identityService.newUser("client1");
				client.setPassword("password");
				identityService.saveUser(client);

				group = identityService.newGroup("cook");
				group.setName("cook");
				group.setType("cook-role");
				identityService.saveGroup(group);
				User cook = identityService.newUser("cook1");
				cook.setPassword("password");
				identityService.saveUser(cook);

				group = identityService.newGroup("sender");
				group.setName("sender");
				group.setType("sender-role");
				identityService.saveGroup(group);
				User sender = identityService.newUser("sender1");
				sender.setPassword("password");
				identityService.saveUser(sender);

			}
		};
	}

}
