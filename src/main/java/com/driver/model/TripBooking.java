package com.driver.model;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "tripbooking")
@Builder

public  class TripBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tripBookingId;

    private String fromLocation;

    private String toLocation;

    private Integer distanceInKm;

    @Enumerated(value = EnumType.STRING)
    private  TripStatus status;

    private Integer bill;

    @JoinColumn
    @ManyToOne
    private Driver driver;

    @JoinColumn
    @ManyToOne
    private Customer customer;

    public TripBooking() {
    }

    public TripBooking(Integer tripBookingId, String fromLocation, String toLocation, Integer distanceInKm,
                       TripStatus status, Integer bill, Driver driver, Customer customer) {
        this.tripBookingId = tripBookingId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.distanceInKm = distanceInKm;
        this.status = status;
        this.bill = bill;
        this.driver = driver;
        this.customer = customer;
    }

    public Integer getTripBookingId() {
        return tripBookingId;
    }

    public void setTripBookingId(Integer tripBookingId) {
        this.tripBookingId = tripBookingId;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public Integer getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(Integer distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public Integer getBill() {
        return bill;
    }

    public void setBill(Integer bill) {
        this.bill = bill;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "TripBooking{" +
                "fromLocation='" + fromLocation + '\'' +
                ", toLocation='" + toLocation + '\'' +
                '}';
    }
}