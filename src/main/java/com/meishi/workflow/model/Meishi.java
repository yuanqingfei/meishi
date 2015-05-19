package com.meishi.workflow.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Meishi extends MongoDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4170527136884410179L;

	private String name;

	private Double price;

	private Double stars = 4.0; // use to mark its popularity
								// 1,1.5,2,2.5,3,3.5,4,4.5,5

	private String description;

	private Byte[] image;

	private MeishiStatus status;

	private List<String> comments;

	private Cook producer;

	public Cook getProducer() {
		return producer;
	}

	public void setProducer(Cook producer) {
		this.producer = producer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getStars() {
		return stars;
	}

	public void setStars(Double stars) {
		this.stars = stars;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public MeishiStatus getStatus() {
		return status;
	}

	public void setStatus(MeishiStatus status) {
		this.status = status;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Meishi [name=" + name + ", price=" + price + ", stars=" + stars + ", description=" + description
				+ ", image=" + Arrays.toString(image) + ", status=" + status + ", comments="
				+ comments + ", producer=" + producer + "]";
	}

	// public Boolean isAvaiable(){
	// for(Producer p : makers){
	// return true;
	// }
	// return false;
	// }
	//
	// public Producer findProducer(){
	// Collections.sort(makers);
	// for(Producer p : makers){
	// if(p.getStatus().equals(WorkerStatus.READY)){
	// return p;
	// }
	// }
	// return null;
	// }
	//
	// public Transporter findTransporter(){
	// Collections.sort(transporters);
	// for(Transporter p : transporters){
	// if(p.getStatus().equals(WorkerStatus.READY)){
	// return p;
	// }
	// }
	// return null;
	// }
	//
	// public Meishi(){
	//
	// }
	
	

}
