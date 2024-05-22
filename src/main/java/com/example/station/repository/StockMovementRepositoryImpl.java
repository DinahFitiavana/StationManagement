package com.example.station.repository;

import com.example.station.config.ConnectToDatabase;
import com.example.station.model.StockMovement;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StockMovementRepositoryImpl implements StockMovementRepository{

    private final ConnectToDatabase connectToDatabase = ConnectToDatabase.getInstance();
    private final Connection connection = connectToDatabase.getConnection();

    @Override
    public List<StockMovement> getAllStockMovement() throws SQLException {
        List<StockMovement> stockMovements = new ArrayList<>();
        String query = "SELECT * FROM stock_movement";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                StockMovement stockMovement = mapResultSetToStockMovement(resultSet);
                stockMovements.add(stockMovement);
            }
        }
        return stockMovements;
    }


    @Override
    public StockMovement getStockMovementById(int id) throws SQLException {
        String query = "SELECT * FROM stock_movement WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToStockMovement(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public StockMovement createStockMovement(StockMovement stockMovement) throws SQLException {
        String query = "INSERT INTO stock_movement (id, date, type, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, stockMovement.getId());
            preparedStatement.setTimestamp(2, stockMovement.getDate());
            preparedStatement.setString(3, stockMovement.getType());
            preparedStatement.setDouble(4, stockMovement.getQuantity());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        stockMovement.setId(generatedKeys.getInt(1));
                        return stockMovement;
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
    public StockMovement updateStockMovement(int id, StockMovement stockMovement) throws SQLException {
        String query = "UPDATE stock_movement SET id = ?, date = ?, type = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, stockMovement.getId());
            preparedStatement.setTimestamp(2, stockMovement.getDate());
            preparedStatement.setString(3, stockMovement.getType());
            preparedStatement.setDouble(4, stockMovement.getQuantity());
            preparedStatement.setInt(5, stockMovement.getId());
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return stockMovement;
            }
        }
        return null;
    }


    private StockMovement mapResultSetToStockMovement(ResultSet resultSet) throws SQLException{
        StockMovement stockMovement = new StockMovement();
        stockMovement.setId(resultSet.getInt("id"));
        stockMovement.setDate(resultSet.getTimestamp("date"));
        stockMovement.setType(resultSet.getString("type"));
        stockMovement.setQuantity(resultSet.getDouble("quantity"));
        return stockMovement;
    }
}
