package com.example.demo.service;

import com.example.demo.util.GeoJsonLoader;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LocationService {
    private final Map<String, Geometry> stateBoundaries;

    @Autowired
    public LocationService(GeoJsonLoader geoJsonLoader) {
        this.stateBoundaries = geoJsonLoader.getStateBoundaries();
    }

    public String getState(double latitude, double longitude) {
        Coordinate coordinate = new Coordinate(longitude, latitude);

        for (Map.Entry<String, Geometry> entry : stateBoundaries.entrySet()) {
            if (entry.getValue().contains(new GeometryFactory().createPoint(coordinate))) {
                return entry.getKey();
            }
        }

        return "State not found";
    }
}
