package com.meishi.workflow.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Administrator extends Worker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2763397533669277779L;

}
