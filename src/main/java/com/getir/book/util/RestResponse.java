package com.getir.book.util;

import lombok.Data;

@Data
public class RestResponse<T> {
    private T data;
    private String messages;

    public RestResponse(T data) {
        this.data = data;
    }

    public static <T> RestResponse<T> of(T t) {
        return new RestResponse<>(t);
    }

    public void setMessages(String messages) {
        if (messages != null && !messages.isEmpty()) {
            this.messages = messages;
        }
    }
}