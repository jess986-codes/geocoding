package com.example.demo.rest;

import com.example.demo.service.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/get-state")
    public String getState(@RequestParam double lat, @RequestParam double lon) {
        return locationService.getState(lat, lon);
    }
}
