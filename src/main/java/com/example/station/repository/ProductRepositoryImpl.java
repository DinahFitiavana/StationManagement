package com.example.station.repository;

import com.example.station.config.ConnectToDatabase;
import com.example.station.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository

public class ProductRepositoryImpl implements ProductRepository {
    private final ConnectToDatabase connectToDatabase = ConnectToDatabase.getInstance();
    private final Connection connection = connectToDatabase.getConnection();
    @Override
    public List<Product> getAllProduct() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                Product product = mapResultSetToProduct(resultSet);
                products.add(product);
            }
        }
        return products;
    }


    @Override
    public Product getProductById(int id) throws SQLException {
        String query = "SELECT * FROM product WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToProduct(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Product createProduct(Product product) throws SQLException {
        String query = "INSERT INTO product (id, id_station, id_product) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setInt(2, product.getIdStation());
            preparedStatement.setInt(2, product.getIdProduct());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        product.setId(generatedKeys.getInt(1));
                        return product;
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
    public Product updateProduct(int id, Product product) throws SQLException {
        String query = "UPDATE product SET id = ?, id_station = ?, id_product = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setInt(2, product.getIdStation());
            preparedStatement.setInt(3, product.getIdProduct());
            preparedStatement.setInt(4, product.getId());
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return product;
            }
        }
        return null;
    }


    private Product mapResultSetToProduct(ResultSet resultSet) throws SQLException{
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setIdStation(resultSet.getInt("id_station"));
        product.setIdProduct(resultSet.getInt("id_product"));
        return product;
    }

}
