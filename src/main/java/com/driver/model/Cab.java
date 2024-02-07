package com.driver.model;

import lombok.Builder;
import org.mockito.internal.verification.DefaultRegisteredInvocations;

import javax.persistence.*;

@Entity
@Table(name = "cab")
@Builder

public  class Cab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer perKmRate;

    private boolean available;
    @OneToOne
    @JoinColumn
    Driver driver;

    public Cab(Integer id, Integer perKmRate, boolean available, Driver driver) {
        this.id = id;
        this.perKmRate = perKmRate;
        this.available = available;
        this.driver = driver;
    }

    public Cab() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(Integer perKmRate) {
        this.perKmRate = perKmRate;
    }

    public boolean getAvailable() {
        return available;

    }

    public  boolean isAvailable(){
        return getAvailable();
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}

