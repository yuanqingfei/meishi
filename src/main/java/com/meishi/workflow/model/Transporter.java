package com.meishi.workflow.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Transporter extends Worker implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 826771960007638958L;

}
