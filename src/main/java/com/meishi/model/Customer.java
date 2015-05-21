package com.meishi.model;

import java.io.Serializable;
import java.util.List;

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
	
	private List<Dish> recent5Dishes;
	
	public List<Dish> getRecent5Dishes() {
		return recent5Dishes;
	}

	public void setRecent5Dishes(List<Dish> recent5Dishes) {
		this.recent5Dishes = recent5Dishes;
	}

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
