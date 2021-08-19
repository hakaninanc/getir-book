package com.getir.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics implements Serializable {

    private Long totalOrderCount;

    private Double totalAmount;

    private Long totalPurchasedBooks;


}
