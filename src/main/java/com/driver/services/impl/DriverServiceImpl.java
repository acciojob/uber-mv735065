package com.driver.services.impl;

import com.driver.model.Cab;
import com.driver.repository.CabRepository;
import com.driver.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Driver;
import com.driver.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepository driverRepository3;

	@Autowired
	CabRepository cabRepository3;

	@Override
	public void register(String mobile, String password){
		//Save a driver in the database having given details and a cab with ratePerKm as 10 and availability as True by default.

		Driver driver=Driver.builder()
				.mobile(mobile)
				.password(password)
				.build();

		Cab cab=Cab.builder()
				.available(true)
				.perKmRate(10)
				.driver(driver)
				.build();


		driverRepository3.save(driver);



	}

	@Override
	public void removeDriver(int driverId){
		// Delete driver without using deleteById function

		Iterable<Driver> drivers=driverRepository3.findAll();

		for(Driver driver : drivers){
			if(driver.getDriverId()==driverId){
				driverRepository3.delete(driver);
				break;
			}
		}




	}

	@Override
	public void updateStatus(int driverId){
		//Set the status of respective car to unavailable

		Cab cab=driverRepository3.findById(driverId).get().getCab();

		cab.setAvailable(false);
		cabRepository3.save(cab);




	}
}
