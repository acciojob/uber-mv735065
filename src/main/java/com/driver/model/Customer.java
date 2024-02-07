package com.driver.model;

import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Builder

public  class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String mobile;
    private  String password;


    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<TripBooking> tripBookingList=new ArrayList<>();

    public Customer(Integer customerId, String mobile, String password, List<TripBooking> tripBookingList) {
        this.customerId = customerId;
        this.mobile = mobile;
        this.password = password;
        this.tripBookingList = tripBookingList;
    }

    public Customer() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TripBooking> getTripBookingList() {
        return tripBookingList;
    }

    public void setTripBookingList(List<TripBooking> tripBookingList) {
        this.tripBookingList = tripBookingList;
    }
}