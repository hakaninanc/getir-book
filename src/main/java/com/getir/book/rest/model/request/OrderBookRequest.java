package com.getir.book.rest.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookRequest {

    @Id
    private Long id;

    private Integer quantity;

}
