package com.example.station.repository;

import com.example.station.config.ConnectToDatabase;
import com.example.station.model.ProductTemplate;
import com.example.station.model.Station;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository

public class ProductTemplateRepositoryImpl implements ProductTemplateRepository{
    private final ConnectToDatabase connectToDatabase = ConnectToDatabase.getInstance();
    private final Connection connection = connectToDatabase.getConnection();

    @Override
    public List<ProductTemplate> getAllProductTemplate() throws SQLException {
        List<ProductTemplate> productTemplates = new ArrayList<>();
        String query = "SELECT * FROM product_template";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                ProductTemplate productTemplate = mapResultSetToProductTemplate(resultSet);
                productTemplates.add(productTemplate);
            }
        }
        return productTemplates;
    }

    @Override
    public ProductTemplate getProductTemplateById(int id) throws SQLException {
        String query = "SELECT * FROM product_template WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToProductTemplate(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public ProductTemplate createProductTemplate(ProductTemplate productTemplate) throws SQLException {
        String query = "INSERT INTO product_template (id, name, unit_price) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, productTemplate.getId());
            preparedStatement.setString(2, productTemplate.getName());
            preparedStatement.setDouble(3, productTemplate.getUnitPrice());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        productTemplate.setId(generatedKeys.getInt(1));
                        return productTemplate;
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
    public ProductTemplate updateProductTemplate(int id, ProductTemplate productTemplate) throws SQLException {
        String query = "UPDATE product_template SET id = ?, name = ?, unit_price = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productTemplate.getId());
            preparedStatement.setString(2, productTemplate.getName());
            preparedStatement.setDouble(3, productTemplate.getUnitPrice());
            preparedStatement.setInt(4, productTemplate.getId());
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return productTemplate;
            }
        }
        return null;
    }

    private ProductTemplate mapResultSetToProductTemplate(ResultSet resultSet) throws SQLException{
        ProductTemplate productTemplate = new ProductTemplate();
        productTemplate.setId(resultSet.getInt("id"));
        productTemplate.setName(resultSet.getString("name"));
        productTemplate.setUnitPrice(resultSet.getDouble("unit_price"));
        return productTemplate;
    }
}
