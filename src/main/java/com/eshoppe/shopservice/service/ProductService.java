package com.eshoppe.shopservice.service;

import com.eshoppe.shopservice.dao.ProductRepository;
import com.eshoppe.shopservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
