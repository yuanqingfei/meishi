package com.meishi.model;

public enum OrderStatus {
	
	CREATED("Your Order Has Been Created!"),
	COOKING("Cook Begin To Take It"),
	COOKED("Cooking Is Done"),
	SENDING("Sender Begin To Take It"),
	SENT("Sending Is Done"),
	SUCCESS("Cash In, Complete!"),
	FAILURE("Customer Is Not Satisfied, Refund");
	
	private String description;
	
	OrderStatus(String description){
		this.description = description;
	}

}
