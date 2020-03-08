package com.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.parking")
@SpringBootApplication
public class ParkinglotsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkinglotsApplication.class, args);
	}
}
