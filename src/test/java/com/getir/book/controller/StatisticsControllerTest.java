package com.getir.book.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(StatisticsController.class)
class StatisticsControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    void show() {

        StatisticsController controller = new StatisticsController();


    }
}