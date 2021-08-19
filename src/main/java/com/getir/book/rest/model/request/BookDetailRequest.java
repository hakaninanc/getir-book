package com.getir.book.rest.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailRequest implements Serializable {

    @NotNull(message = "Stock is required.")
    @Min(value = 1)
    private Integer stock;

}
