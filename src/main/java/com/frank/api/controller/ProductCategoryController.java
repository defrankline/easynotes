package com.frank.api.controller;

import com.frank.api.model.ProductCategory;
import com.frank.api.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProductCategoryController {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    // Get All ProductCategories
    @GetMapping("/product-categories")
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    // Create a new ProductCategory
    @PostMapping("/product-categories")
    public ProductCategory createProductCategory(@Valid @RequestBody ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    // Get a Single ProductCategory
    @GetMapping("/product-categories/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable(value = "id") Long productCategoryId) {
        ProductCategory productCategory = productCategoryRepository.findOne(productCategoryId);
        if(productCategory == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productCategory);
    }

    // Update a ProductCategory
    @PutMapping("/product-categories/{id}")
    public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable(value = "id") Long productCategoryId, @Valid @RequestBody ProductCategory productCategoryDetails) {
        ProductCategory productCategory = productCategoryRepository.findOne(productCategoryId);
        if(productCategory == null) {
            return ResponseEntity.notFound().build();
        }
        productCategory.setName(productCategoryDetails.getName());
        ProductCategory updatedProductCategory = productCategoryRepository.save(productCategory);
        return ResponseEntity.ok(updatedProductCategory);
    }

    // Delete a ProductCategory
    @DeleteMapping("/product-categories/{id}")
    public ResponseEntity<ProductCategory> deleteProductCategory(@PathVariable(value = "id") Long productCategoryId) {
        ProductCategory productCategory = productCategoryRepository.findOne(productCategoryId);
        if(productCategory == null) {
            return ResponseEntity.notFound().build();
        }

        productCategoryRepository.delete(productCategory);
        return ResponseEntity.ok().build();
    }
}