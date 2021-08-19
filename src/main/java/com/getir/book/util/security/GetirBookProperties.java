package com.getir.book.util.security;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("getir-book")
public class GetirBookProperties {

    private String username;

    private String password;

    private String secretKey;

    private Integer expirationInMs;

}
