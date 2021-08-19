package com.getir.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.book.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    void saveBook() throws Exception {

        mock.perform(MockMvcRequestBuilders
                .post("/api/v1/book/save")
                .content(asJsonString(new Book(null, "ReadingIsGood", "Hakan Inanc", 5, 20.0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookId").exists());
    }

//    @Test
//    void updateBook() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .put("/{id}/stock", 1)
//                .content(asJsonString(new BookDetailRequest(1, 8))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath(10).value(8)));
//    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}