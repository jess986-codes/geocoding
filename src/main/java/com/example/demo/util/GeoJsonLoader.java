package com.example.demo.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class GeoJsonLoader {
    private final Map<String, Geometry> stateBoundaries = new HashMap<>();
    private final GeometryFactory geometryFactory = new GeometryFactory();

    public GeoJsonLoader() throws IOException, ParseException {
        loadGeoJson();
    }

    private void loadGeoJson() throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/australian_states.geojson");
        JsonNode root = mapper.readTree(inputStream);

        GeoJsonReader geoJsonReader = new GeoJsonReader();
        for (JsonNode feature : root.get("features")) {
            String stateName = feature.get("properties").get("STATE_NAME").asText();
            String geometryJson = feature.get("geometry").toString();
            Geometry geometry = geoJsonReader.read(geometryJson);
            stateBoundaries.put(stateName, geometry);
        }
    }

    public Map<String, Geometry> getStateBoundaries() {
        return stateBoundaries;
    }
}
