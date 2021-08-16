package com.getir.book.service;

import com.getir.book.dao.OrderRepository;
import com.getir.book.impl.extension.exception.ResourceNotFoundException;
import com.getir.book.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public Order getOrder(Long orderId) {

        try {
            final Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
            return order;
        } catch (ResourceNotFoundException e) {
            return null;
        }

    }



}
