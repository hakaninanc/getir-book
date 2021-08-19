package com.getir.book.rest.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest implements Serializable {

    @NotNull(message = "Customer name is required.")
    private String name;

    @NotNull(message = "Customer surname is required.")
    private String surname;

    @Indexed(unique=true)
    @Email
    @NotNull(message = "Email is required.")
    private String mail;

}

