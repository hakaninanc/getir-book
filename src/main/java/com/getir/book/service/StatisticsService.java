package com.getir.book.service;

import com.getir.book.dao.BookRepository;
import com.getir.book.dao.OrderRepository;
import com.getir.book.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@Service
@Slf4j
public class StatisticsService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Long getTotalOrderCount() {
        return orderRepository.count();
    }

//    public Double getTotalOrderAmount() {
//        return bookRepository.getTotalAmount();
//    }

//    public Double getTotalOrderAmount() {
//        MatchOperation matchOperation = Aggregation.match(new Criteria("amount"));
//        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, "amount");
//        Aggregation aggregation = newAggregation(matchOperation, sortOperation);
//        AggregationResults result = mongoTemplate.aggregate(aggregation, "order", Order.class);
//        return orderRepository.getTotalAmount();
//    }

    public Integer getTotalPurchasedBooksCount() {

        MatchOperation matchOperation = Aggregation.match(new Criteria("book.price"));
        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, "book.price");
        Aggregation aggregation = newAggregation(matchOperation, sortOperation);
        AggregationResults result = mongoTemplate.aggregate(aggregation, "order", Order.class);
        return null;
//        return orderRepository.getTotalPurchasedBooks();
    }


}
