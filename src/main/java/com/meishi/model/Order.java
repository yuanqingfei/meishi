package com.meishi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order extends MongoDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3111494353502958534L;

	private Date orderTime;

	private Date expectTime;

	private Date completeTime;

	private Administrator administrator;

	private List<Cook> cooks;

	private Customer customer;

	private List<Sender> senders;

	private List<Dish> foods;

	private String addtionalRequirement;

	private Double totalPrice;

	private String deliveryAddress;

	private Double[] location;

	private List<OrderStatusEntry> statuses;

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Double[] getLocation() {
		return location;
	}

	public void setLocation(Double[] location) {
		this.location = location;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public List<Dish> getFoods() {
		return foods;
	}

	public void setFoods(List<Dish> foods) {
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

	public List<Cook> getCooks() {
		return cooks;
	}

	public void setCooks(List<Cook> cooks) {
		this.cooks = cooks;
	}

	public List<Sender> getSenders() {
		return senders;
	}

	public void setSenders(List<Sender> senders) {
		this.senders = senders;
	}

	@Override
	public String toString() {
		return "Order [orderTime=" + orderTime + ", expectTime=" + expectTime + ", admin=" + administrator
				+ ", orderBy=" + customer + ", foods=" + foods + ", addtionalRequirement=" + addtionalRequirement
				+ ", totalPrice=" + totalPrice + ", location=" + deliveryAddress + ", statuses=" + statuses + "]";
	}

}
