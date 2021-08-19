package com.getir.book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@Slf4j
public class ReadingIsGoodApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ReadingIsGoodApplication.class, args);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getMessage());
        }
    }
}

