package com.driver.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Admin;
import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.AdminRepository;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository1;

	@Autowired
	DriverRepository driverRepository1;

	@Autowired
	CustomerRepository customerRepository1;

	@Override
	public void adminRegister(Admin admin) {
		//Save the admin in the database

		Admin admin1=Admin.builder()
				.username(admin.getUsername())
				.password(admin.getPassword())
				.build();
adminRepository1.save(admin1);


	}

	@Override
	public Admin updatePassword(Integer adminId, String password) {
		//Update the password of admin with given id

		Optional<Admin> optionalAdmin=adminRepository1.findById(adminId);
		if(optionalAdmin.isEmpty()) return null;

		Admin admin=optionalAdmin.get();
		admin.setPassword(password);

		admin=adminRepository1.save(admin);

return admin;
	}

	@Override
	public void deleteAdmin(int adminId){
		// Delete admin without using deleteById function


		Optional<Admin> optionalAdmin=adminRepository1.findById(adminId);
		if(optionalAdmin.isEmpty()) return;

		Admin admin=optionalAdmin.get();

            adminRepository1.deleteById(adminId);



	}

	@Override
	public List<Driver> getListOfDrivers() {
		//Find the list of all drivers
    List<Driver> list=new ArrayList<>();

		Iterable<Driver> drivers=driverRepository1.findAll();

		for(Driver driver : drivers) {
			list.add(driver);

		}


	return list;

	}

	@Override
	public List<Customer> getListOfCustomers() {
		//Find the list of all customers
		List<Customer> list=new ArrayList<>();

		Iterable<Customer> customers=customerRepository1.findAll();

		for(Customer customer: customers) list.add(customer);
		return list;

	}

}
