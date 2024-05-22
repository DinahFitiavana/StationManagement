package com.example.station.service;

import com.example.station.model.ProductTemplate;
import com.example.station.repository.ProductRepositoryImpl;
import com.example.station.repository.ProductTemplateRepositoryImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductTemplateService {

    private ProductTemplateRepositoryImpl productTemplateRepository;
    public ProductTemplateService(ProductTemplateRepositoryImpl productTemplateRepository){
        this.productTemplateRepository = productTemplateRepository;
    }

    public List<ProductTemplate> getAllProductTemplate() throws SQLException{
        return productTemplateRepository.getAllProductTemplate();
    }

    public ProductTemplate getProductTemplateById(int id) throws SQLException{
        return productTemplateRepository.getProductTemplateById(id);
    }

    public ProductTemplate createProductTemplate(ProductTemplate productTemplate) throws SQLException{
        return productTemplateRepository.createProductTemplate(productTemplate);
    }

    public ProductTemplate updateProductTemplate(int id, ProductTemplate productTemplate) throws SQLException {
        return productTemplateRepository.updateProductTemplate(id, productTemplate);
    }
}
