package com.meishi.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.meishi.repository.MeishiRepositoryApplication;

@Configuration
@ComponentScan
@Import(MeishiRepositoryApplication.class)
public class MeishiServiceApplication {

//	@Bean
//	public AdministratorService adminService(){
//		return new AdministratorServiceImpl();
//	}
//	
//	@Bean
//	public CookService cookService(){
//		return new CookServiceImpl();
//	}
//	
//	@Bean
//	public CustomerService customerService(){
//		return new CustomerServiceImpl();
//	}
//	
//	@Bean
//	public SenderService senderService(){
//		return new SenderServiceImpl();
//	}
//	
//	@Bean
//	public DishService dishService(){
//		return new DishServiceImpl();
//	}
//	
//	@Bean
//	public OrderService orderService(){
//		return new OrderServiceImpl();
//	}
//	
//	@Bean
//	public WorkflowService workflowService(){
//		return new WorkflowServiceImpl();
//	}

	
}
