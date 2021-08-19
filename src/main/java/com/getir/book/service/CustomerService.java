package com.getir.book.service;


import com.getir.book.dao.CustomerRepository;
import com.getir.book.dao.OrderRepository;
import com.getir.book.model.Customer;
import com.getir.book.model.Order;
import com.getir.book.util.exception.ResourceNotFoundException;
import com.getir.book.util.exception.SaveErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public Customer saveCustomer(Customer customer) {
        customer.setId(sequenceGeneratorService.generateSequence(Customer.SEQUENCE_NAME));
        final Customer newCustomer = customerRepository.save(customer);
        if (newCustomer == null || newCustomer.getId() == null) {
            throw new SaveErrorException("An error occurred while creating customer.");
        }
        log.info("Create successfull");
        return newCustomer;
    }

    public List<Order> getOrders(Long customerId) {
        try {
            Pageable pageableRequest = PageRequest.of(0, 1);
            Page<Order> page = orderRepository.findAll(pageableRequest);
            List<Order> customersOrders = page.getContent();
            return customersOrders;


        } catch (Exception e) {
            log.error(e.getMessage(), e.getMessage());
        }
        return null;
    }

    public Customer getCustomerById(Long id) {
        final Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + id));
        return customer;
    }
}


