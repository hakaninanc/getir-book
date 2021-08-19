package com.getir.book.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }
}
