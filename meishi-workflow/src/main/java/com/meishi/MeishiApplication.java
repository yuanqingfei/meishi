package com.meishi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.meishi.security.BasicAuthenticationProvider;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MeishiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeishiApplication.class, args);

	}

	@Configuration
	@EnableMongoRepositories
	public static class RepositoryApplication extends AbstractMongoConfiguration {

		@Override
		protected String getDatabaseName() {
			return "meishiTest";
		}

		@Override
		protected UserCredentials getUserCredentials() {
			return new UserCredentials("meishiTestUser", "654321");
		}
		
		@Override
		public Mongo mongo() throws Exception {
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			return mongoClient;
		}

//		 @Override
//		 protected String getDatabaseName() {
//		 return "meishi";
//		 }
//		
//		 @Override
//		 protected UserCredentials getUserCredentials() {
//		 return new UserCredentials("meishiUser", "123456");
//		 }


	}

	@Configuration
	@EnableWebSecurity
	public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Bean
		public AuthenticationProvider authenticationProvider() {
			return new BasicAuthenticationProvider();
		}
		
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			// @formatter:off
//			auth.inMemoryAuthentication()
//				.withUser("roy")
//					.password("spring")
//					.roles("USER");
//			// @formatter:on
//		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authenticationProvider(authenticationProvider()).csrf().disable().authorizeRequests().anyRequest()
					.authenticated().and().httpBasic();
		}
	}

}
