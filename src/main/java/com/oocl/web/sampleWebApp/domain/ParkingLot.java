package com.oocl.web.sampleWebApp.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Id;

public class ParkingLot {
    @Id
    private Long Id;

    @Column(name = "parkingLotID", unique = true, nullable = false)
    private String parkingLotId;

    @Range(min = 1, max = 100)
    private int capacity;
    public ParkingLot() {}

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
