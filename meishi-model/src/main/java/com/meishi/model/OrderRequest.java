package com.meishi.model;

public class OrderRequest {
	
    private String  dishName;

    /**
     * I will use this to pass two parts. The format will be like
     * "detailAddress:LocationX,LocationY"
     */
    private String address;

    private String clientId;
    
    public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}
