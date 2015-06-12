package com.meishi.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Person extends MongoDocument implements Serializable {
	private static final long serialVersionUID = 1898355582020364397L;
	
	/**
	 * can be id card number or email address, must be unique
	 * Customer/Admin use email. Cook and Sender use id card number 
	 * 
	 * mandatory
	 */
	@Indexed
	protected String identity;
	
	/**
	 * mandatory
	 * for customer, password need complicated. 6 digits, at least 1 upper case. 1 lower case.
	 * 
	 * remove Ignore so that it can be saved to mongoDB from Android
	 */
	protected String password;
	
	/**
	 * mandatory, can be nickname or real name.
	 */
	protected String name;
	
	/**
	 * mandatory, used to contact offline by Admin.
	 */
	protected String telephoneNumber;
	
	/**
	 * mandatory, used to convert to location.
	 */
	protected String address;
	
	/**
	 * optional
	 */
	protected Gender gender;
	
	/**
	 * optional 
	 */
	protected Integer age;
	
	
	/**
	 * mandatory, but will be offered by front client which convert address to it.
	 */
	@GeoSpatialIndexed
	protected double[] location;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double[] getLocation() {
		return location;
	}

	public void setLocation(double[] location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", age=" + age + ", telephoneNumber=" + telephoneNumber
				+ ", identity=" + identity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identity == null) ? 0 : identity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (identity == null) {
			if (other.identity != null)
				return false;
		} else if (!identity.equals(other.identity))
			return false;
		return true;
	}

	
}
