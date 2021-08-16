package com.getir.book.service;


import com.getir.book.dao.CustomerRepository;
import com.getir.book.impl.extension.exception.ResourceNotFoundException;
import com.getir.book.model.Book;
import com.getir.book.model.Customer;
import com.getir.book.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService {

    final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        customerRepository.save(customer);
        log.info("Create successfull");
        return customer;
    }

//    public List<Order> findAllOrders(Long customerId) {
//        try {
//            customerRepository.findAllById(customerId).
//                    orElseThrow(() -> new ResourceNotFoundException("Book is not found for this id :: " + bookId));
//
//
//            Book updatedBook = bookRepository.save(_book);
//            return updatedBook;
//        } catch (Exception e) {
//            return null;
//        }
//    }

}
