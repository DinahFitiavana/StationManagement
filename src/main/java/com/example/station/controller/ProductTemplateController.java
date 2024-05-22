package com.example.station.controller;

import com.example.station.model.Product;
import com.example.station.model.ProductTemplate;
import com.example.station.service.ProductService;
import com.example.station.service.ProductTemplateService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductTemplateController {
    private ProductTemplateService productTemplateService;

    public ProductTemplateController(ProductTemplateService productTemplateService) {
        this.productTemplateService = productTemplateService;
    }

    @GetMapping("/productTemplate")
    public List<ProductTemplate> getAllProductTemplate() throws SQLException{
        return productTemplateService.getAllProductTemplate();
    }

    @GetMapping("/productTemplate/{id}")
    public ProductTemplate getProductTemplateById(int id) throws SQLException{
        return productTemplateService.getProductTemplateById(id);
    }

    @PostMapping("/productTemplate")
    public ProductTemplate createProductTemplate(ProductTemplate productTemplate) throws SQLException{
        return productTemplateService.createProductTemplate(productTemplate);
    }

    @PutMapping("/productTemplate/{id}")
    ProductTemplate updateProductTemplate(int id, ProductTemplate productTemplate) throws SQLException {
        return productTemplateService.updateProductTemplate(id, productTemplate);
    }
}
