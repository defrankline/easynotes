package com.frank.api.controller;

import com.frank.api.model.Brand;
import com.frank.api.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BrandController {

    @Autowired
    BrandRepository brandRepository;

    // Get All Brands
    @GetMapping("/brands")
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    // Create a new Brand
    @PostMapping("/brands")
    public Brand createBrand(@Valid @RequestBody Brand brand) {
        return brandRepository.save(brand);
    }

    // Get a Single Brand
    @GetMapping("/brands/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable(value = "id") Long brandId) {
        Brand brand = brandRepository.findOne(brandId);
        if(brand == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(brand);
    }

    // Update a Brand
    @PutMapping("/brands/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable(value = "id") Long brandId, @Valid @RequestBody Brand brandDetails) {
        Brand brand = brandRepository.findOne(brandId);
        if(brand == null) {
            return ResponseEntity.notFound().build();
        }
        brand.setName(brandDetails.getName());
        Brand updatedBrand = brandRepository.save(brand);
        return ResponseEntity.ok(updatedBrand);
    }

    // Delete a Brand
    @DeleteMapping("/brands/{id}")
    public ResponseEntity<Brand> deleteBrand(@PathVariable(value = "id") Long brandId) {
        Brand brand = brandRepository.findOne(brandId);
        if(brand == null) {
            return ResponseEntity.notFound().build();
        }

        brandRepository.delete(brand);
        return ResponseEntity.ok().build();
    }
}