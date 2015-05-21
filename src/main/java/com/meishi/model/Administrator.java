package com.meishi.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Administrator extends Worker implements Serializable {

	private List<Worker> workers;

	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2763397533669277779L;

}
