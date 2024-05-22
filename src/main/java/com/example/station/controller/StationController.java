package com.example.station.controller;

import com.example.station.model.ProductTemplate;
import com.example.station.model.Station;
import com.example.station.service.ProductTemplateService;
import com.example.station.service.StationService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StationController {
    private StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/station")
    public List<Station> getAllStation() throws SQLException{
        return stationService.getAllStation();
    }

    @GetMapping("/station/{id}")
    public Station getStationById(int id) throws SQLException{
        return stationService.getStationById(id);
    }

    @PostMapping("/station")
    public Station createStation(Station station) throws SQLException{
        return stationService.createStation(station);
    }

    @PutMapping("/station/{id}")
    public Station updateStation(int id, Station station) throws SQLException{
        return stationService.updateStation(id, station);
    }
}
