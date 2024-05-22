package com.example.station.repository;

import com.example.station.config.ConnectToDatabase;
import com.example.station.model.Station;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository

public class StationRepositoryImpl implements StationRepository{

    private final ConnectToDatabase connectToDatabase = ConnectToDatabase.getInstance();
    private final Connection connection = connectToDatabase.getConnection();

    @Override
    public List<Station> getAllStation() throws SQLException {
        List<Station> stations = new ArrayList<>();
        String query = "SELECT * FROM station";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                Station station = mapResultSetToStation(resultSet);
                stations.add(station);
            }
        }
        return stations;
    }



    @Override
    public Station getStationById(int id) throws SQLException {
        String query = "SELECT * FROM station WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToStation(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Station createStation(Station station) throws SQLException {
        String query = "INSERT INTO station (id, name, location) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, station.getId());
            preparedStatement.setString(2, station.getName());
            preparedStatement.setString(3, station.getLocation());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        station.setId(generatedKeys.getInt(1));
                        return station;
                    } else {
                        throw new SQLException("Failed to retrieve generated ID.");
                    }
                }
            } else {
                throw new SQLException("Failed to insert into database.");
            }
        }
    }

    @Override
    public Station updateStation(int id, Station station) throws SQLException {
        String query = "UPDATE station SET id = ?, name = ?, location = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, station.getId());
            preparedStatement.setString(2, station.getName());
            preparedStatement.setString(3, station.getLocation());
            preparedStatement.setInt(4, station.getId());
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return station;
            }
        }
        return null;
    }

    private Station mapResultSetToStation(ResultSet resultSet) throws  SQLException{
        Station station = new Station();
        station.setId(resultSet.getInt("id"));
        station.setName(resultSet.getString("name"));
        station.setLocation(resultSet.getString("location"));
        return station;
    }

    /*public double getQuantityRemain(Timestamp date) throws SQLException{
        String query1 = "select sum(quantity)  from stock_movement where type = 'supply'";
        String query2 = "select sum(quantity)  from stock_movement where type = 'sale'";

    }*/
}
