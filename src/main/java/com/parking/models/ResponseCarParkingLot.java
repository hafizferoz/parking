package com.parking.models;

import java.io.Serializable;

public class ResponseCarParkingLot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int parkingLotNumber;
	private int slotNumber;
	private int parkingId;
	
	public int getParkingLotNumber() {
		return parkingLotNumber;
	}
	public int getSlotNumber() {
		return slotNumber;
	}
	public int getParkingId() {
		return parkingId;
	}
	public void setParkingLotNumber(int parkingLotNumber) {
		this.parkingLotNumber = parkingLotNumber;
	}
	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}
	public void setParkingId(int parkingId) {
		this.parkingId = parkingId;
	}

}
