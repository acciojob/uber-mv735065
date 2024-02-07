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

    private Integer perkmRate;

    private boolean available;
    @OneToOne
    @JoinColumn
    Driver driver;

    public Cab(Integer id, Integer perkmRate, boolean available, Driver driver) {
        this.id = id;
        this.perkmRate = perkmRate;
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

    public Integer getPerkmRate() {
        return perkmRate;
    }

    public void setPerkmRate(Integer perkmRate) {
        this.perkmRate = perkmRate;
    }

    public boolean isAvailable() {
        return available;
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

