package com.takemissinghome.utils;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DefaultResponse<T> {

    private final int statusCode;
    private final String message;
    private final T data;

    @Builder
    public DefaultResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static <T> DefaultResponse<T> res(int statusCode, String message) {
        return res(statusCode, message, null);
    }

    public static <T> DefaultResponse<T> res(int statusCode, String message, T t) {
        return DefaultResponse.<T>builder()
                .data(t)
                .statusCode(statusCode)
                .message(message)
                .build();
    }
}
