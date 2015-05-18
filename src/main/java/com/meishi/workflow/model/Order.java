package com.meishi.workflow.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3111494353502958534L;

	@Id
	private String id;

	private Date orderTime;

	private Date expectTime;

	private Administrator administrator;

	private Cook cook;

	private Customer customer;

	private Transporter transporter;

	private List<Meishi> foods;

	private String addtionalRequirement;

	private Double totalPrice;

	private Location deliveryAddress;

	private List<OrderStatusEntry> statuses;

	public Location getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Location deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Cook getCook() {
		return cook;
	}

	public void setCook(Cook cook) {
		this.cook = cook;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Transporter getTransporter() {
		return transporter;
	}

	public void setTransporter(Transporter transporter) {
		this.transporter = transporter;
	}

	public List<OrderStatusEntry> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<OrderStatusEntry> statuses) {
		this.statuses = statuses;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getExpectTime() {
		return expectTime;
	}

	public void setExpectTime(Date expectTime) {
		this.expectTime = expectTime;
	}

	public List<Meishi> getFoods() {
		return foods;
	}

	public void setFoods(List<Meishi> foods) {
		this.foods = foods;
	}

	public String getAddtionalRequirement() {
		return addtionalRequirement;
	}

	public void setAddtionalRequirement(String addtionalRequirement) {
		this.addtionalRequirement = addtionalRequirement;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Order [orderTime=" + orderTime + ", expectTime=" + expectTime + ", admin=" + administrator
				+ ", cookBy=" + cook + ", orderBy=" + customer + ", sendBy=" + transporter + ", foods=" + foods
				+ ", addtionalRequirement=" + addtionalRequirement + ", totalPrice=" + totalPrice + ", location="
				+ deliveryAddress + ", statuses=" + statuses + "]";
	}

}
