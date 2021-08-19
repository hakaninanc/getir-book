package com.getir.book.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Long id;

    @NotNull(message = "Customer name is required.")
    private String name;

    @NotNull(message = "Customer surname is required.")
    private String surname;

    @Indexed(unique=true)
    @Email()
    @NotNull(message = "Email is required.")
    private String mail;

}
