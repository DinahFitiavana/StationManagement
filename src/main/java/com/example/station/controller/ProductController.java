package com.example.station.controller;

import com.example.station.model.Product;
import com.example.station.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public List<Product> getAllProduct() throws SQLException {
        return productService.getAllProduct();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(int id) throws SQLException{
        return productService.getProductById(id);
    }

    @PostMapping("/product")
    public Product createProduct(Product product) throws SQLException{
        return productService.createProduct(product);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(int id, Product product) throws SQLException{
        return productService.updateProduct(id,product);
    }
}
