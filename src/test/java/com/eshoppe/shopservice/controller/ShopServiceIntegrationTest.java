package com.eshoppe.shopservice.controller;

import com.eshoppe.shopservice.model.PurchaseRequestDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
public class ShopServiceIntegrationTest {

    @Autowired
    private ShopController shopController;

    @Test
    public void testHello() throws Exception {
        Assert.assertEquals("Hello from E Shoppe !!", shopController.hello());
    }

    @Test
    public void testCustomers() throws Exception {
        Assert.assertEquals(4, shopController.getCustomers().size());
    }

    @Test
    public void testProducts() throws Exception {
        Assert.assertEquals(6, shopController.getProducts().size());
    }

    @Test
    public void purchaseProduct() throws Exception {
        PurchaseRequestDTO purchaseRequestDTO = new PurchaseRequestDTO();
        List<String> productCodes = new ArrayList<>();
        productCodes.add("sgball");
        productCodes.add("sgbat");
        productCodes.add("kitbag");
        purchaseRequestDTO.setProductCodes(productCodes);
        purchaseRequestDTO.setCustomerId(1L);

        Assert.assertEquals(3, shopController.purchaseProducts(purchaseRequestDTO).getProducts().size());
    }

    @Test
    public void purchaseProduct_wrongCustomerID() {
        PurchaseRequestDTO purchaseRequestDTO = new PurchaseRequestDTO();
        List<String> productCodes = new ArrayList<>();
        productCodes.add("sgball");
        productCodes.add("sgbat");
        productCodes.add("kitbag");
        purchaseRequestDTO.setProductCodes(productCodes);
        purchaseRequestDTO.setCustomerId(9L);

       try {
           shopController.purchaseProducts(purchaseRequestDTO).getProducts().size();
       } catch (ResponseStatusException rse) {
           Assert.assertEquals(HttpStatus.BAD_REQUEST, rse.getStatus());
           Assert.assertEquals("Customer not found.", rse.getReason());
       }
    }

    @Test
    public void purchaseProduct_inSufficientPoints() {
        PurchaseRequestDTO purchaseRequestDTO = new PurchaseRequestDTO();
        List<String> productCodes = new ArrayList<>();
        productCodes.add("sgball");
        productCodes.add("sgbat");
        productCodes.add("kitbag");
        purchaseRequestDTO.setProductCodes(productCodes);
        purchaseRequestDTO.setCustomerId(4L);

        try {
            shopController.purchaseProducts(purchaseRequestDTO).getProducts().size();
        } catch (ResponseStatusException rse) {
            Assert.assertEquals(HttpStatus.BAD_REQUEST, rse.getStatus());
            Assert.assertEquals("Insufficient Active Day Points Balance.", rse.getReason());
        }
    }

    @Test
    public void purchaseProduct_invalidProduct() {
        PurchaseRequestDTO purchaseRequestDTO = new PurchaseRequestDTO();
        List<String> productCodes = new ArrayList<>();
        productCodes.add("abc");
        productCodes.add("sgbat");
        productCodes.add("xyz");
        purchaseRequestDTO.setProductCodes(productCodes);
        purchaseRequestDTO.setCustomerId(4L);

        try {
            shopController.purchaseProducts(purchaseRequestDTO).getProducts().size();
        } catch (ResponseStatusException rse) {
            Assert.assertEquals(HttpStatus.BAD_REQUEST, rse.getStatus());
            Assert.assertEquals("Invalid Product selected.", rse.getReason());
        }
    }


}
