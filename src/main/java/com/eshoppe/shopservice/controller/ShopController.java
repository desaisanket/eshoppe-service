package com.eshoppe.shopservice.controller;

import com.eshoppe.shopservice.exception.PurchaseException;
import com.eshoppe.shopservice.model.Customer;
import com.eshoppe.shopservice.model.Product;
import com.eshoppe.shopservice.model.PurchaseRequestDTO;
import com.eshoppe.shopservice.model.PurchaseResponseDTO;
import com.eshoppe.shopservice.service.CustomerService;
import com.eshoppe.shopservice.service.ProductService;
import com.eshoppe.shopservice.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ShopController {

    Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/hello")
    public String hello(){
        logger.info("Hello, Application is running.");
        return "Hello from E Shoppe !!";
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        logger.info("Fetching all Customers.");
        return customerService.getAllCustomers();
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        logger.info("Fetching all Customers.");
        return productService.getAllProducts();
    }

    @PostMapping("/products/purchase")
    public PurchaseResponseDTO purchaseProducts(@RequestBody @Valid PurchaseRequestDTO purchaseRequestDTO){
        logger.info("Product purchase start");
        try{
           return purchaseService.purchaseProducts(purchaseRequestDTO);
        } catch (PurchaseException pEx){
            throw new ResponseStatusException(
                    pEx.getStatus(), pEx.getMessage(), pEx);
        }
    }
}
