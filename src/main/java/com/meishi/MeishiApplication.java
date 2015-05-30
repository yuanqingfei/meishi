package com.meishi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.meishi.security.BasicAuthenticationProvider;
import com.meishi.test.repository.app.MeishiRepositoryApplicationForTest;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import(MeishiRepositoryApplicationForTest.class)
public class MeishiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeishiApplication.class, args);

	}

	@Configuration
	@EnableWebSecurity
	public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Bean
		public AuthenticationProvider authenticationProvider() {
			return new BasicAuthenticationProvider();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authenticationProvider(authenticationProvider())
			.csrf().disable()
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and().httpBasic();
		}
	}

}
