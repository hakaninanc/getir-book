package com.getir.book.dao;

import com.getir.book.model.Order;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {
    @Query("{'customer.id': ?0}")
    List<Order> findByCustomerId(final Long customerId);

    @Aggregation(pipeline = {"{$group: { _id: '', total: {$sum: book.price}}}"})
    Double getTotalAmount();

    @Aggregation(pipeline = {"{$group: { _id: '', total: {$sum: book.stock}}}"})
    Integer getTotalPurchasedBooks();
}
