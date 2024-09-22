package com.example.UberProject_LocationService.controllers;


import com.example.UberProject_LocationService.dto.DriverLocationDto;
import com.example.UberProject_LocationService.dto.NearbyDriversRequestDto;
import com.example.UberProject_LocationService.dto.SaveDriverLocationRequestDto;
import com.example.UberProject_LocationService.services.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/drivers")
    public ResponseEntity<Boolean> saveDriverLocation(@RequestBody SaveDriverLocationRequestDto saveDriverLocationRequestDto) {
        try {
            Boolean response = locationService.saveDriverLocation(saveDriverLocationRequestDto.getDriverId(), saveDriverLocationRequestDto.getLatitude(), saveDriverLocationRequestDto.getLongitude());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @PostMapping ("/nearby/drivers")
    public ResponseEntity<List<DriverLocationDto>> getNearbyDrivers(@RequestBody NearbyDriversRequestDto nearbyDriversRequestDto) {
        try {
            List<DriverLocationDto> drivers = locationService.getNearByDrivers(nearbyDriversRequestDto.getLatitude(), nearbyDriversRequestDto.getLongitude());
            return new ResponseEntity<>(drivers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}

