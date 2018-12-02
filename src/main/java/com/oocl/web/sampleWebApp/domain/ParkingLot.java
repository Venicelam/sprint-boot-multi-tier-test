package com.oocl.web.sampleWebApp.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;


@Entity
@Table(name = "parking_lot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "parkingLotID", length = 15, unique = true, nullable = false)
    private String parkingLotId;

    @Range(min = 1, max = 100)
    @Column(name = "capacity", length = 3, nullable = false)
    private int capacity;
    public ParkingLot(String lot, int i) {}

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
