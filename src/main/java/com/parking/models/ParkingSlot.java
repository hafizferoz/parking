package com.parking.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ParkingSlot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="parking_slot_id")
	private Integer parkingSlotNo;
	
	@JoinColumn(name = "parking_lot_id")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ParkingLot parkingLotNumber;
	
	@Column(name="parking_cost")
	private double billAmount;
	
	@JoinColumn(name = "parkingId")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Car car;
	
	@Column(name = "start_time")
	private LocalDateTime startParkingTime;
	
	@Column(name = "end_time")
	private LocalDateTime endParkingTime;
	
	public Integer getParkingSlotNo() {
		return parkingSlotNo;
	}
	public ParkingLot getParkingLotNumber() {
		return parkingLotNumber;
	}
	
	public Car getCar() {
		return car;
	}
	public LocalDateTime getStartParkingTime() {
		return startParkingTime;
	}
	public LocalDateTime getEndParkingTime() {
		return endParkingTime;
	}
	public void setParkingSlotNo(Integer parkingSlotNo) {
		this.parkingSlotNo = parkingSlotNo;
	}
	public void setParkingLotNumber(ParkingLot parkingLotNumber) {
		this.parkingLotNumber = parkingLotNumber;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	public void setStartParkingTime(LocalDateTime startParkingTime) {
		this.startParkingTime = startParkingTime;
	}
	public void setEndParkingTime(LocalDateTime endParkingTime) {
		this.endParkingTime = endParkingTime;
	}
	public double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}
	

}
