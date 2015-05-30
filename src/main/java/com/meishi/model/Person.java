package com.meishi.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import com.fasterxml.jackson.annotation.JsonIgnore;

@CompoundIndex(name = "identity_name_index", def = "{'identity': 1, 'name': 2}")
public class Person extends MongoDocument implements Serializable {
	/**
	 * 
	 */
	protected String identity;
	
	@JsonIgnore
	protected String password;
	
	private static final long serialVersionUID = 1898355582020364397L;
	protected String name;
	protected Gender gender;
	protected Integer age;
	protected String telephoneNumber;
	
	protected String streetName;
	protected String districtName;
	protected String commercialCircle;
	
	@GeoSpatialIndexed
	protected double[] location;
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getCommercialCircle() {
		return commercialCircle;
	}

	public void setCommercialCircle(String commercialCircle) {
		this.commercialCircle = commercialCircle;
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
