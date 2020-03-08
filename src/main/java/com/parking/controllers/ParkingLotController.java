package com.parking.controllers;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.parking.models.Car;
import com.parking.models.CarDto;
import com.parking.models.ParkingLot;
import com.parking.models.ParkingRule;
import com.parking.models.ParkingSlot;
import com.parking.models.RequestPark;
import com.parking.models.RequestParkingLot;
import com.parking.models.ResponsePark;
import com.parking.models.ResponseUnpark;
import com.parking.services.CarService;
import com.parking.services.ParkingLotService;
import com.parking.services.ParkingSlotService;

@Controller
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired 
    private CarService carService;
    
    @Autowired 
    private ParkingSlotService parkingSlotService;

    @ResponseBody
    @RequestMapping(value = "/api/parkingLots", method = RequestMethod.POST)
    public ResponseEntity<Integer> create(@RequestBody RequestParkingLot parkingLotReq) {
        ParkingLot parkingLot = new ParkingLot();
    	modelMapper.map(parkingLotReq, parkingLot);
    	parkingLot.setEmptySlotCount(parkingLotReq.getCapacity());
		return new ResponseEntity<Integer> (parkingLotService.save(parkingLot).getParkingLotNumber() ,HttpStatus.OK);
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/api/park", method = RequestMethod.POST)
    public ResponseEntity<ResponsePark> park(@RequestBody RequestPark parkingReq) throws Exception {
    	Car car =new Car();
    	ParkingSlot parkingSlot = new ParkingSlot();
    	Car carDto = parkingReq.getCar();
		modelMapper.map(carDto, car);
		parkingSlot.setCar(car);
		ResponsePark responsePark = new ResponsePark();
    	if(ParkingRule.fillFirst.equals(ParkingRule.valueOf(parkingReq.getParkingRule()))){
    		List<ParkingLot> parkingLots= findAll();
    		parkingLots.sort((ParkingLot p1, ParkingLot p2)->p1.getParkingLotNumber().compareTo(p2.getParkingLotNumber()));
    		Optional<ParkingLot> parkingLotOpt = parkingLots.stream().filter(parkingLot-> parkingLot.getEmptySlotCount()>0).findFirst();
    		ParkingLot parkingLot = parkingLotOpt.orElseThrow(() ->new Exception("No Parking lot available"));
    				parkingSlot.setParkingLotNumber(parkingLot);
    				responsePark.setParkingLotNumber(parkingLot.getParkingLotNumber());
    	}
    	else {
    		List<ParkingLot> parkingLots= findAll();
    		//parkingLots.sort((ParkingLot p1, ParkingLot p2)->  ((p2.getEmptySlotCount()/p2.getCapacity())*100) - ((p1.getEmptySlotCount()/p1.getCapacity())*100));
    		Optional<ParkingLot> parkingLotOpt = parkingLots.stream().max((ParkingLot p1, ParkingLot p2) -> ((p1.getEmptySlotCount()*100/p1.getCapacity())) - ((p2.getEmptySlotCount()*100/p2.getCapacity())));
    		Collections.sort(parkingLots, new Comparator<ParkingLot>() {

				@Override
				public int compare(ParkingLot p1, ParkingLot p2) {
					return 0;
				}
			});
    		ParkingLot parkingLot = parkingLotOpt.orElseThrow(() ->new Exception("No Parking lot available"));
    				parkingSlot.setParkingLotNumber(parkingLot);
    				responsePark.setParkingLotNumber(parkingLot.getParkingLotNumber());
    	}
    	parkingSlot.setStartParkingTime(LocalDateTime.now());
    	parkingSlot.getParkingLotNumber().setEmptySlotCount(parkingSlot.getParkingLotNumber().getEmptySlotCount()-1);
    	car.setParkingSlotNo(parkingSlot);
    	parkingSlotService.save(parkingSlot);
    	responsePark.setParkingId(parkingSlot.getCar().getParkingId());
		return new ResponseEntity<ResponsePark> (responsePark ,HttpStatus.OK);
    }

    @RequestMapping(value = "/api/unpark/{licenseNumber}", method = RequestMethod.GET)
	public ResponseEntity<ResponseUnpark> unpark(@PathVariable String licenseNumber) throws Exception {
		ResponseUnpark res = new ResponseUnpark();
		List<Car> cars = carService.findByLicenseNumber(licenseNumber);
		if (cars != null) {
			Optional<Car> carOpt = cars.stream().filter(c -> c.getParkingSlotNo().getEndParkingTime()==null).findFirst();
			Car car = carOpt.orElseThrow(()-> new Exception("No such car parked"));
			ParkingSlot parkingSlot = car.getParkingSlotNo();
			if (parkingSlot.getEndParkingTime() == null) {
				parkingSlot.setEndParkingTime(LocalDateTime.now());
				long hours = parkingSlot.getStartParkingTime().until(parkingSlot.getEndParkingTime(), ChronoUnit.HOURS);
				parkingSlot.setBillAmount(hours > 0 ? hours * 1 : 1); // per hour rate default 1 RM
				parkingSlot.getParkingLotNumber()
						.setEmptySlotCount(parkingSlot.getParkingLotNumber().getEmptySlotCount() + 1);
				parkingSlotService.save(parkingSlot);
			}
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
			res.setBillAmount(currencyFormatter.format(parkingSlot.getBillAmount()));
			res.setDurationOfParking(
					parkingSlot.getStartParkingTime().until(parkingSlot.getEndParkingTime(), ChronoUnit.HOURS));
			res.setPerHourRate(1);
			CarDto carDto = new CarDto();
			carDto.setColor(car.getColor());
			carDto.setLicenseNumber(car.getLicenseNumber());
			res.setCarDto(carDto);
		}
		return new ResponseEntity<ResponseUnpark>(res, HttpStatus.OK);
	}

    
    
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/api/parkinglot/{id}", method = RequestMethod.DELETE)
    public void updateParkingLot(@PathVariable  int id) {
        parkingLotService.delete(id);
    }

   // @ResponseBody
    //@RequestMapping(value = "/api/parkinglot/all", method = RequestMethod.GET)
    public List<ParkingLot> findAll() {
        
        return (List<ParkingLot>) parkingLotService.findByEmptySlotCountGreaterThan(0);
    }

    @ResponseBody
    @RequestMapping(value = "/api/parkingLots/{id}", method = RequestMethod.GET)
    public ResponseEntity<ParkingLot> parkingLotById(@PathVariable  int id) {
        ParkingLot parkingLot = parkingLotService.findOne(id);
        ParkingLot parkingLotDto = new ParkingLot();
        if(parkingLot!=null){
        parkingLotDto.setName(parkingLot.getName());
        parkingLotDto.setCapacity(parkingLot.getCapacity());
        parkingLotDto.setParkingLotNumber(parkingLot.getParkingLotNumber());
        parkingLotDto.setEmptySlotCount(parkingLot.getEmptySlotCount());
        }
        return new ResponseEntity<ParkingLot> (parkingLotDto ,HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/api/find/{carLicenseNumber}", method = RequestMethod.GET)
	public ResponseEntity<ResponsePark> parkingLotByCarLicenseNumber(@PathVariable String carLicenseNumber)
			throws Exception {
		ResponsePark res = new ResponsePark();
		List<Car> cars = carService.findByLicenseNumber(carLicenseNumber);
		if (cars != null) {
			Optional<Car> carOpt = cars.stream().filter(c -> c.getParkingSlotNo().getEndParkingTime() == null)
					.findFirst();
			Car car = carOpt.orElseThrow(() -> new Exception("No such car parked"));
				res.setParkingId(car.getParkingId());
				res.setParkingLotNumber(car.getParkingSlotNo().getParkingLotNumber().getParkingLotNumber());
				res.setSlotNumber(car.getParkingSlotNo().getParkingSlotNo());
		}
		return new ResponseEntity<ResponsePark>(res, HttpStatus.OK);
	}
    @ResponseBody
    @RequestMapping(value = "/api//parkingLots/{parkingLotNumber}/sameColor", method = RequestMethod.GET)
	public ResponseEntity<List<CarDto>> parkingLotByCarColor(@PathVariable Integer parkingLotNumber) {
		//ParkingLot parkingLot = parkingLotService.findOne(parkingLotNumber);
		 //parkingLot.getCars().stream().filter(car -> car.getParkingSlotNo().getEndParkingTime() == null).collect(Collectors.toList());
    	List<Car> cars =carService.findAllCarsForParkingLot(parkingLotNumber);
    	List<CarDto> sameColorCars = new ArrayList<CarDto>();
		List<Car> carList = new ArrayList<Car>(cars);
		cars.forEach(car -> {
			if (verfiyColor(car, carList)) {
				CarDto cardto = new CarDto();
				modelMapper.map(car, cardto);
				sameColorCars.add(cardto);
			}
		});
		return new ResponseEntity<List<CarDto>>(sameColorCars, HttpStatus.OK);

	}


	private boolean verfiyColor(Car colorCar, List<Car> carList) {
		long count = carList.stream().filter( car-> car.getColor().equalsIgnoreCase(colorCar.getColor())).count();
		return count>1;
	}

}
