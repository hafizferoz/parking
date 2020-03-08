package com.parking.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.parking.models.ParkingLot;

@Repository
@Transactional
public interface ParkingLotService extends CrudRepository<ParkingLot, Integer> {

    List<ParkingLot> findByName(String location);
    List<ParkingLot> findByEmptySlotCountGreaterThan(int emptySlotCount);
}
