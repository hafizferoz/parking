package com.parking.services;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.parking.models.ParkingSlot;

@Repository
@Transactional
public interface ParkingSlotService extends CrudRepository<ParkingSlot, Integer> {


}
