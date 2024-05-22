package com.example.station.repository;

import com.example.station.model.ProductTemplate;
import com.example.station.model.Station;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface ProductTemplateRepository {
    List<ProductTemplate> getAllProductTemplate() throws SQLException;
    ProductTemplate getProductTemplateById(int id) throws SQLException;
    ProductTemplate createProductTemplate(ProductTemplate productTemplate) throws SQLException;
    ProductTemplate updateProductTemplate(int id, ProductTemplate productTemplate) throws SQLException;
}
