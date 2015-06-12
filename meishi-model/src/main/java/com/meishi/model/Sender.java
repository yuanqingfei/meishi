package com.meishi.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class Sender extends Worker implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 826771960007638958L;

}
