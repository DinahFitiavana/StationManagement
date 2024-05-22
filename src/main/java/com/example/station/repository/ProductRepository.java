package com.example.station.repository;

import com.example.station.model.Product;
import com.example.station.model.Station;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface ProductRepository {
    List<Product> getAllProduct() throws SQLException;
    Product getProductById(int id) throws SQLException;
    Product createProduct(Product product) throws SQLException;
    Product updateProduct(int id, Product product) throws SQLException;
}
