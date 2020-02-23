package com.eshoppe.shopservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "CUSTOMER")
public class Customer {

    @Id
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "CUSTOMER_USERNAME")
    private String customerUsername;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "ACTIVE_DAY_POINTS")
    private Long activeDayPoints;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getActiveDayPoints() {
        return activeDayPoints;
    }

    public void setActiveDayPoints(Long activeDayPoints) {
        this.activeDayPoints = activeDayPoints;
    }
}
