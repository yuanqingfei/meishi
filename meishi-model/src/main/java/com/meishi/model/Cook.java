package com.meishi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cook extends Worker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6288236442983642481L;

	private String healthCard;

	private List<String> dishIds = new ArrayList<String>();

	public List<String> getDishIds() {
		return dishIds;
	}

	public String getHealthCard() {
		return healthCard;
	}

	public void setHealthCard(String healthCard) {
		this.healthCard = healthCard;
	}

	@Override
	public String toString() {
		return "Cook " + super.toString() + " [healthCard=" + healthCard + "]";
	}

}
