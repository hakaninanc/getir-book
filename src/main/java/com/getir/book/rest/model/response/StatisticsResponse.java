package com.getir.book.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsResponse implements Serializable {

    private Long totalOrderCount;

    private Double totalAmount;

    private Integer totalPurchasedBooks;


}
