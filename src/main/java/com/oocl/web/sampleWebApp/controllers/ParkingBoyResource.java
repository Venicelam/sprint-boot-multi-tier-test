package com.oocl.web.sampleWebApp.controllers;

import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingBoyRepository;
import com.oocl.web.sampleWebApp.domain.ParkingLotRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/parkingboys")
public class ParkingBoyResource {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @GetMapping
    public ResponseEntity<ParkingBoyResponse[]> getAll() {
        final ParkingBoyResponse[] parkingBoys = parkingBoyRepository.findAll().stream()
            .map(ParkingBoyResponse::create)
            .toArray(ParkingBoyResponse[]::new);
        return ResponseEntity.ok(parkingBoys);
    }

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<ParkingBoyResponse> getByEmployeeId(@PathVariable String employeeId) {
        ParkingBoyResponse parkingBoyResponse = ParkingBoyResponse.create(parkingBoyRepository.findByEmployeeId(employeeId).getEmployeeId());
        return ResponseEntity.ok(parkingBoyResponse);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody ParkingBoy parkingBoy){
        if(parkingBoyRepository.save(parkingBoy)!=null) {
            return ResponseEntity.created(URI.create("/parkingboys/" + parkingBoy.getId())).build();
        }
        return ResponseEntity.badRequest().build();
    }
}
