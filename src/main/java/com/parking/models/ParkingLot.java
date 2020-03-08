package com.parking.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "parking_lot")
public class ParkingLot implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="parking_lot_id")
    private Integer parkingLotNumber;
	
	@Column(name="name")
    private String name;
	
	@Column(name="capacity")
    private Integer capacity=0;
	
	@Column(name="empty_slots")
	private Integer emptySlotCount=0;
	

    @OneToMany(mappedBy="parkingSlotNo", fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Car> cars;


    public Integer getParkingLotNumber() {
		return parkingLotNumber;
	}

	public void setParkingLotNumber(Integer parkingLotNumber) {
		this.parkingLotNumber = parkingLotNumber;
	}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

   /* public double getParkingCost() {
        return parkingCost;
    }

    public void setParkingCost(double parkingCost) {
        this.parkingCost = parkingCost;
    }*/

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Integer getEmptySlotCount() {
		return emptySlotCount;
	}

	public void setEmptySlotCount(Integer emptySlotCount) {
		this.emptySlotCount = emptySlotCount;
	}

	@Override
    public String toString() {
        return "ParkingLot{" +
                "parkingLotNumber=" + parkingLotNumber +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", emptySlotCount=" + emptySlotCount +
                ", cars=" + cars +
                '}';
    }
}
