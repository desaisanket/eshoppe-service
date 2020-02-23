package com.eshoppe.shopservice.service;

import com.eshoppe.shopservice.dao.CustomerRepository;
import com.eshoppe.shopservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id){
        return  customerRepository.findById(id).get();
    }
}
