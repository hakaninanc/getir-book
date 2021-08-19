package com.getir.book.rest.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.getir.book.model.Book;
import com.getir.book.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse implements Serializable {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate deliveryDate;

    private String status;

    private Customer customer;

    private List<Book> books;

}

