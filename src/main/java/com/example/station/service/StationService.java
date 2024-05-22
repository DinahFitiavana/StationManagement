package com.example.station.service;

import com.example.station.model.Station;
import com.example.station.repository.ProductTemplateRepositoryImpl;
import com.example.station.repository.StationRepositoryImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StationService {

    private StationRepositoryImpl stationRepository;
    public StationService(StationRepositoryImpl stationRepository){
        this.stationRepository = stationRepository;
    }

    public List<Station> getAllStation() throws SQLException{
        return stationRepository.getAllStation();
    }

    public Station getStationById(int id) throws SQLException{
        return stationRepository.getStationById(id);
    }

    public Station createStation(Station station) throws SQLException{
        return stationRepository.createStation(station);
    }

    public Station updateStation(int id, Station station) throws SQLException{
        return stationRepository.updateStation(id, station);
    }
}
