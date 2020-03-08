package com.parking.models;

import java.io.Serializable;

public class RequestPark implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Car car;
	private String parkingRule;
	
	public Car getCar() {
		return car;
	}
	public String getParkingRule() {
		return parkingRule;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public void setParkingRule(String parkingRule) {
		this.parkingRule = parkingRule;
	}

}
