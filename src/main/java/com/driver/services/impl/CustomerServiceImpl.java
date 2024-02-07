package com.driver.services.impl;

import com.driver.model.*;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		Customer customer1=Customer.builder()
				.tripBookingList(customer.getTripBookingList())
				.password(customer.getPassword())
				.mobile(customer.getMobile())
				.build();



		customerRepository2.save(customer1);




	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
//		Optional<Customer> optionalCustomer=customerRepository2.findById(customerId);

		Iterable<Customer> all= customerRepository2.findAll();

		for(Customer customer: all){
			Integer id=customer.getCustomerId();
			if(customerId==id){
				customerRepository2.delete(customer);
			}
		}

	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE).
		// If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query

		Iterable<Driver> allDrivers= driverRepository2.findAll();

		int idMin=Integer.MAX_VALUE;
		int cabId=1;
  // finding the driver who is available
		for(Driver driver : allDrivers){
			Cab cab=driver.getCab();
			if(cab.isAvailable() && idMin>driver.getDriverId()) {
				cab.setAvailable(false);
				idMin=driver.getDriverId();
				cabId=cab.getId();

			}


		}

		if(idMin==Integer.MAX_VALUE){
			throw new Exception("No cab available!");
		}


		Customer customer=customerRepository2.findById(customerId).get();

		if(customer==null) throw new Exception("customer is not registerd");


		Driver driver=driverRepository2.findById(idMin).get();



		int costOfKm=driver.getCab().getPerKmRate();
       int totalCost=costOfKm*distanceInKm;


		TripBooking tripBooking=TripBooking.builder()
				.fromLocation(fromLocation)
				.toLocation(toLocation)
				.bill(totalCost)
				.customer(customer)
				.distanceInKm(distanceInKm)
				.status(TripStatus.CONFIRMED)
				.driver(driver)
				.build();



		  customer.getTripBookingList().add(tripBooking);

		  driver.getTripBookingList().add(tripBooking);
		tripBooking=tripBookingRepository2.save(tripBooking);
		  return tripBooking;



	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly

		Optional<TripBooking> optionalTripBooking=tripBookingRepository2.findById(tripId);
		if(optionalTripBooking.isEmpty()) return ;

		TripBooking tripBooking=optionalTripBooking.get();

		tripBooking.setStatus(TripStatus.CANCELED);

		Driver driver=tripBooking.getDriver();
		Customer customer=tripBooking.getCustomer();

		driver.getCab().setAvailable(true);

		// search of trip in custor triporder list;

		tripBookingRepository2.save(tripBooking);


	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly

		Optional<TripBooking> optionalTripBooking=tripBookingRepository2.findById(tripId);
		if(optionalTripBooking.isEmpty()) return ;

		TripBooking tripBooking=optionalTripBooking.get();

		tripBooking.setStatus(TripStatus.COMPLETED);

		Driver driver=tripBooking.getDriver();
		Customer customer=tripBooking.getCustomer();

		driver.getCab().setAvailable(true);
		tripBookingRepository2.save(tripBooking);

	}
}
