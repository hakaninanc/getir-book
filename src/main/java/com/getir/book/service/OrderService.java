package com.getir.book.service;

import com.getir.book.dao.OrderRepository;
import com.getir.book.model.Book;
import com.getir.book.model.Customer;
import com.getir.book.model.Order;
import com.getir.book.util.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
public class OrderService {

    private ReentrantLock reentrantlock = new ReentrantLock();

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public Order saveOrder(Order order) {
        final List<Book> demandedBooks = new ArrayList<>();
        try {
            reentrantlock.lock();
            bookService.getAvailableBooks(order, demandedBooks);
            if(demandedBooks.size() == 0){
                return null;
            }

            final Customer customer = customerService.getCustomerById((order.getCustomer().getId()));

            order.setId(sequenceGeneratorService.generateSequence(Order.SEQUENCE_NAME));
            order.setCreateDate(LocalDate.now());
            order.setCustomer(customer);
            order.setBooks(demandedBooks);

            final Order newOrder = orderRepository.save(order);
            log.info("Order created");
            bookService.updateStock(demandedBooks);

            reentrantlock.unlock();

            return newOrder;

        } catch (Exception e) {
            log.error(e.getMessage(), e.getMessage());
        }
        return null;

    }

    public Order getOrder(Long orderId) {
        try {
            final Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
            return order;
        } catch (ResourceNotFoundException e) {
            return null;
        }

    }

    public List<Order> findAllOrders() {
        try {
            List<Order> orderList = orderRepository.findAll();
            return orderList;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Order> getCustomersOrders(Long customerId) {
        try {
            return orderRepository.findByCustomerId(customerId);
        } catch (Exception e) {
            return null;
        }
    }


}
