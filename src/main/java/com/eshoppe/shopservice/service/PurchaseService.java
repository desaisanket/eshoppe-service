package com.eshoppe.shopservice.service;

import com.eshoppe.shopservice.dao.CustomerRepository;
import com.eshoppe.shopservice.dao.ProductRepository;
import com.eshoppe.shopservice.exception.PurchaseException;
import com.eshoppe.shopservice.model.Customer;
import com.eshoppe.shopservice.model.Product;
import com.eshoppe.shopservice.model.PurchaseRequestDTO;
import com.eshoppe.shopservice.model.PurchaseResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    Logger logger = LoggerFactory.getLogger(PurchaseService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public PurchaseResponseDTO purchaseProducts(PurchaseRequestDTO purchaseRequest) throws PurchaseException {
        PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO();
        logger.info("Fetching Customer by ID:{} ",purchaseRequest.getCustomerId());
        Optional<Customer> customer = customerRepository.findById(purchaseRequest.getCustomerId());
        if (!customer.isPresent()){
            logger.info("No Customer found by ID:{} ",purchaseRequest.getProductCodes());
            throw new PurchaseException(HttpStatus.BAD_REQUEST, "Customer not found.");
        }

        purchaseRequest.setProductCodes(purchaseRequest.getProductCodes().stream().distinct().collect(Collectors.toList()));
        logger.info("Fetching Products by product codes:{} ",purchaseRequest.getProductCodes());
        List<Product> products = productRepository.findDistinctByProductCodeIn(purchaseRequest.getProductCodes());

        if(null != products &&
            products.size() == purchaseRequest.getProductCodes().size()){
            Long totalPointsCost = 0L;
            for(Product product : products){
               totalPointsCost += product.getPoints_cost();
            }
            if (totalPointsCost > customer.get().getActiveDayPoints()){
                logger.info("Customer does not have sufficient points - Customer ID:{} ",purchaseRequest.getCustomerId());
                throw new PurchaseException(HttpStatus.BAD_REQUEST, "Insufficient Active Day Points Balance.");
            } else {
                customer.get().setActiveDayPoints(customer.get().getActiveDayPoints() - totalPointsCost);
                customerRepository.save(customer.get());
                logger.info("Products purchased successfully - Customer ID:{} ",purchaseRequest.getCustomerId());
                purchaseResponseDTO.setMessage("Products purchased successfully");
                purchaseResponseDTO.setProducts(products);
            }
        } else {
            logger.info("No Products found by product codes:{} ",purchaseRequest.getProductCodes());
            throw new PurchaseException(HttpStatus.BAD_REQUEST, "Invalid Product selected.");
        }

        return purchaseResponseDTO;
    }
}
