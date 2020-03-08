package com.parking.models;

import java.io.Serializable;

public class ResponseParkingLot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private int capacity;
	private int parkingLotNumber;
	private int emptySlotCount;
	
	public String getName() {
		return name;
	}
	public int getCapacity() {
		return capacity;
	}
	public int getParkingLotNumber() {
		return parkingLotNumber;
	}
	public int getEmptySlotCount() {
		return emptySlotCount;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setParkingLotNumber(int parkingLotNumber) {
		this.parkingLotNumber = parkingLotNumber;
	}
	public void setEmptySlotCount(int emptySlotCount) {
		this.emptySlotCount = emptySlotCount;
	}
}
