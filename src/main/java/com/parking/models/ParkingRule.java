package com.parking.models;

import java.io.Serializable;

public enum ParkingRule implements Serializable {	

	fillFirst("fillFirst"), evenDistribution("evenDistribution");
	
	private String parkingRule;

	ParkingRule(String parkingRule){
		this.parkingRule =parkingRule;
	}
	public String toString(){
		return parkingRule;
		
	}
}
