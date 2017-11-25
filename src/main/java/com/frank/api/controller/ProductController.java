package com.frank.api.controller;

import com.frank.api.model.Product;
import com.frank.api.repository.ProductRepository;
import com.frank.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    // Get All Products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //Get all products - paginated
    @GetMapping("/products/paginated")
    public Page<Product> getPaginatedProducts(@RequestParam Integer page,@RequestParam Integer perPage) {
        return productService.getPaginatedProduct(page,perPage);
    }

    // Create a new Product
    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    // Get a Single Product
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(product);
    }

    // Update a Product
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId, @Valid @RequestBody Product productDetails) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setProductCategory(productDetails.getProductCategory());
        product.setBrand(productDetails.getBrand());

        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    // Delete a Product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") Long productId) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }

}