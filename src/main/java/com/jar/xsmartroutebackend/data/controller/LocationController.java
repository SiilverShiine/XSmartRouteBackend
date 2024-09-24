package com.jar.xsmartroutebackend.data.controller;

import com.jar.xsmartroutebackend.data.model.Location;
import com.jar.xsmartroutebackend.data.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public void addLocation(@RequestBody Location location) {
        locationService.addLocation(location);
    }

    @GetMapping
    public List<Location> getAllLocations() throws ExecutionException, InterruptedException {
        return locationService.getAllLocations();
    }
}
