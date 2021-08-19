package com.getir.book.service;

import com.getir.book.dao.OrderRepository;
import com.getir.book.model.Book;
import com.getir.book.model.Customer;
import com.getir.book.model.Order;
import com.getir.book.util.exception.ResourceNotFoundException;
import com.getir.book.util.exception.SaveErrorException;
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
        reentrantlock.lock();
        bookService.getAvailableBooks(order, demandedBooks);
        if (demandedBooks.size() == 0) {
            return null;
        }

        final Customer customer = customerService.getCustomerById((order.getCustomer().getId()));

        order.setId(sequenceGeneratorService.generateSequence(Order.SEQUENCE_NAME));
        order.setCreateDate(LocalDate.now());
        order.setCustomer(customer);
        order.setBooks(demandedBooks);

        final Order newOrder = orderRepository.save(order);
        if (newOrder == null || newOrder.getId() == null) {
            throw new SaveErrorException("An error occurred while creating order.");
        }

        log.info("Order created");
        bookService.updateStock(demandedBooks);

        reentrantlock.unlock();

        return newOrder;

    }

    public Order getOrder(Long orderId) throws ResourceNotFoundException {
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
        return order;

    }

    public List<Order> findAllOrders() throws ResourceNotFoundException {
        return orderRepository.findAll();
    }

    public List<Order> getCustomersOrders(Long customerId) throws ResourceNotFoundException {
        return orderRepository.findByCustomerId(customerId);
    }


}
