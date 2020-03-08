package com.parking.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="car")
public class Car implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="parkingId")
    private Integer parkingId;
	
	@Column(name="license_plate")
    private String licenseNumber;
	
	@Column(name="color")
    private String color;
	
    @JoinColumn(name = "parking_slot_id")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private ParkingSlot parkingSlotNo;


	public ParkingSlot getParkingSlotNo() {
		return parkingSlotNo;
	}

	public void setParkingSlotNo(ParkingSlot parkingSlotNo) {
		this.parkingSlotNo = parkingSlotNo;
	}


	public Car() {
    }
	

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
    @Override
    public String toString() {
        return "Car{" +
        		"parkingId='" + parkingId + '\'' +
                "licenseNumber='" + licenseNumber + '\'' +
                ", color='" + color + '\'' + "parkingSlotNo" +'\'' + parkingSlotNo +
                '}';
    }
}
