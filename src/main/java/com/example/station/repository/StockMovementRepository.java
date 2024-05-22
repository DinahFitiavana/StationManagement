package com.example.station.repository;

import com.example.station.model.Station;
import com.example.station.model.StockMovement;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface StockMovementRepository {

    List<StockMovement> getAllStockMovement() throws SQLException;
    StockMovement getStockMovementById(int id) throws SQLException;
    StockMovement createStockMovement(StockMovement stockMovement) throws SQLException;
    StockMovement updateStockMovement(int id, StockMovement stockMovement) throws SQLException;
}
