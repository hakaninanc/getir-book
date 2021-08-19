package com.getir.book.dao;

import com.getir.book.model.Order;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {
    @Query("{'customer.id': ?0}")
    List<Order> findByCustomerId(final Long customerId);

    @Query("{$where : 'return this.date.getMonth() == 5'}")
    List<Order> findByDateBetween(LocalDate from, LocalDate to);

    @Aggregation(pipeline = {
            "{$group: { _id.books: '', total: {$sum: book.price}}}"
    })
    Double getTotalAmount();

    //@Aggregation("{ '$group' : { 'book.price' : null, 'total' : { $sum: '$price' } } }")
    //Integer getTotalPurchasedBooks();
}
