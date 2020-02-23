package com.eshoppe.shopservice.dao;

import com.eshoppe.shopservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
