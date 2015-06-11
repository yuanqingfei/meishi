package com.meishi.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer extends Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1754352325109465960L;
	/**
	 * optional, if customer prefer some kind of dish style, system will filter dish when 
	 * search.
	 */
	private String appetite;
	
	/**
	 * mandatory, it will be update by system.
	 */
	private Double accountBalance = 0.0;

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAppetite() {
		return appetite;
	}

	public void setAppetite(String appetite) {
		this.appetite = appetite;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
