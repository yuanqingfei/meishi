package com.meishi.workflow.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer extends Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1754352325109465960L;
	private String appetite;
	private Boolean registered;
	private String registerId;

	public String getAppetite() {
		return appetite;
	}

	public void setAppetite(String appetite) {
		this.appetite = appetite;
	}

	public Boolean getRegistered() {
		return registered;
	}

	public void setRegistered(Boolean registered) {
		this.registered = registered;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	@Override
	public String toString() {
		return "Consumer: " + super.toString() + " [appetite=" + appetite + ", registered=" + registered
				+ ", registerId=" + registerId + "]";
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
