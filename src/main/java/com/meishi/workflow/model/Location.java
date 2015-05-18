package com.meishi.workflow.model;

public class Location {

	private String streetName; 

	private String districtName;

	private String commercialCircle;

	private Integer coordinationX;

	private Integer coordinationY;

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

	public Integer getCoordinationX() {
		return coordinationX;
	}

	public void setCoordinationX(Integer coordinationX) {
		this.coordinationX = coordinationX;
	}

	public Integer getCoordinationY() {
		return coordinationY;
	}

	public void setCoordinationY(Integer coordinationY) {
		this.coordinationY = coordinationY;
	}

}
