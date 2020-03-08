package com.parking.models;

import java.io.Serializable;

public class ResponseUnpark implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CarDto car;
	private String billAmount;
	private long durationOfParking;
	private int perHourRate;

	public CarDto getCar() {
		return car;
	}
	public String getBillAmount() {
		return billAmount;
	}
	public long getDurationOfParking() {
		return durationOfParking;
	}
	public int getPerHourRate() {
		return perHourRate;
	}
	public void setCarDto(CarDto car) {
		this.car = car;
	}
	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}
	public void setDurationOfParking(long durationOfParking) {
		this.durationOfParking = durationOfParking;
	}
	public void setPerHourRate(int perHourRate) {
		this.perHourRate = perHourRate;
	}

}
