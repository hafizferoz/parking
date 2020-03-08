package com.parking.models;

import java.io.Serializable;

public class CarDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer parkingId;
	
    private String licenseNumber;
	
    private String color;
	
    private Integer parkingSlotNo;


	public Integer getParkingSlotNo() {
		return parkingSlotNo;
	}

	public void setParkingSlotNo(Integer parkingSlotNo) {
		this.parkingSlotNo = parkingSlotNo;
	}


	public CarDto() {
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
