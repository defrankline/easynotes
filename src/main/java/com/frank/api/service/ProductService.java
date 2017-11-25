package com.frank.api.service;

import com.frank.api.model.Product;
import com.frank.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getPaginatedProduct(Integer page, Integer perPage){
        return productRepository.findAll(new PageRequest(page,perPage));
    }
}
