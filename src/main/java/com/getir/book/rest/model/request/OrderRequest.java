package com.getir.book.rest.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest implements Serializable {

    private Long customerId;

    private List<OrderBookRequest> books;
}

