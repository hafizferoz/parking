package com.parking.services;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.parking.models.*;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CarService extends CrudRepository<Car, Integer> {

   Car findByLicenseNumber(String licensePlate);
   
   @Query("Select c from Car c where c.parkingSlotNo.parkingLotNumber.parkingLotNumber = ?1")
   List<Car> findAllCarsForParkingLot(Integer parkingLotNumber);
   

}

