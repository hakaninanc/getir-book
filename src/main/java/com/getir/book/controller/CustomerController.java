package com.getir.book.controller;

import com.getir.book.dao.CustomerRepository;
import com.getir.book.model.Customer;
import com.getir.book.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@Validated @RequestBody Customer customer) {

        final Customer newCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(newCustomer);
    }
}
