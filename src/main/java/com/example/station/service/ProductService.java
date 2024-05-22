package com.example.station.service;

import com.example.station.model.Product;
import com.example.station.repository.ProductRepository;
import com.example.station.repository.ProductRepositoryImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {
    private ProductRepositoryImpl productRepository;
    public ProductService(ProductRepositoryImpl productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() throws SQLException{
        return productRepository.getAllProduct();
    }

    public Product getProductById(int id) throws SQLException{
        return productRepository.getProductById(id);
    }

    public Product createProduct(Product product) throws SQLException{
        return productRepository.createProduct(product);
    }

    public Product updateProduct(int id, Product product) throws SQLException{
        return productRepository.updateProduct(id,product);
    }
}
