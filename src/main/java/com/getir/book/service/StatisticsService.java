package com.getir.book.service;

import com.getir.book.dao.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StatisticsService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Long getTotalOrderCount() {
        return orderRepository.count();
    }

    public Double getTotalOrderAmount() {
        return orderRepository.getTotalAmount();
    }

    public Integer getTotalPurchasedBooksCount() {
        return orderRepository.getTotalPurchasedBooks();
    }


}
