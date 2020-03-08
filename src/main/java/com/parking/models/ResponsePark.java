package com.parking.models;

import java.io.Serializable;

public class ResponsePark implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer parkingId;
	private Integer parkingLotNumber;
	private Integer slotNumber;
	
	public Integer getParkingId() {
		return parkingId;
	}
	public Integer getParkingLotNumber() {
		return parkingLotNumber;
	}
	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}
	public void setParkingLotNumber(Integer parkingLotNumber) {
		this.parkingLotNumber = parkingLotNumber;
	}
	public Integer getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(Integer slotNumber) {
		this.slotNumber = slotNumber;
	}
}
