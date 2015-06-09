package com.meishi.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MongoDocument implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -682801822534337330L;
	
	@Id
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
