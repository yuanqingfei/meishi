package com.meishi.workflow.model;

import java.io.Serializable;

public class Cook extends Worker implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6288236442983642481L;

	private Boolean available;

	private String healthCard;

	public String getHealthCard() {
		return healthCard;
	}

	public void setHealthCard(String healthCard) {
		this.healthCard = healthCard;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Producer " + super.toString() + " [available=" + available + ", healthCard=" + healthCard + "]";
	}
	
	

}
