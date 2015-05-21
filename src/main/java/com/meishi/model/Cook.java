package com.meishi.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cook extends Worker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6288236442983642481L;

	private String healthCard;

	private List<Dish> dishes;

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public String getHealthCard() {
		return healthCard;
	}

	public void setHealthCard(String healthCard) {
		this.healthCard = healthCard;
	}

	@Override
	public String toString() {
		return "Producer " + super.toString() + " [healthCard=" + healthCard + "]";
	}

}
