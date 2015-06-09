package com.meishi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Administrator extends Worker implements Serializable {

	private List<String> directWorkerIds = new ArrayList<String>();

	public List<String> getDirectWorkerIds() {
		return directWorkerIds;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2763397533669277779L;

}
