package com.meishi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.meishi.test.repository.MeishiRepositoryApplicationForTest;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import(MeishiRepositoryApplicationForTest.class)
public class MeishiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeishiApplication.class, args);
	}

}
