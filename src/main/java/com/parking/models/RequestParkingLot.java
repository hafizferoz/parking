package com.parking.models;

import java.io.Serializable;

public class RequestParkingLot implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer capacity;
	
	public String getName() {
		return name;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

}
