package com.example.station.service;

import com.example.station.model.StockMovement;
import com.example.station.repository.StockMovementRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StockMovementService {

    private StockMovementRepositoryImpl stockMovementRepository;
    public StockMovementService(StockMovementRepositoryImpl stockMovementRepository){
        this.stockMovementRepository = stockMovementRepository;
    }

    public List<StockMovement> getAllStockMovement() throws SQLException{
        return stockMovementRepository.getAllStockMovement();
    }

    public StockMovement getStockMovementById(int id) throws SQLException{
        return stockMovementRepository.getStockMovementById(id);
    }

    public StockMovement createStockMovement(StockMovement stockMovement) throws SQLException{
        return stockMovementRepository.createStockMovement(stockMovement);
    }

    public StockMovement updateStockMovement(int id, StockMovement stockMovement) throws SQLException{
        return stockMovementRepository.updateStockMovement(id,stockMovement);
    }
}
