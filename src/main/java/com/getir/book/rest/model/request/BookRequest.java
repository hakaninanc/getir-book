package com.getir.book.rest.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest implements Serializable {

    @Indexed(unique=true)
    @NotEmpty(message = "Book name is required.")
    private String name;

    @NotEmpty(message = "Author name is required.")
    private String author;

    @NotNull(message = "Stock is required.")
    @Min(value = 1)
    @Max(value = 10)
    private Integer stock;

    @NotNull(message = "Price is required.")
    @Min(value = 0)
    private Double price;

}
