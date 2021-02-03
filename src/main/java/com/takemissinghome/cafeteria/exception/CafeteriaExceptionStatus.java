package com.takemissinghome.cafeteria.exception;

import lombok.Getter;

@Getter
public enum  CafeteriaExceptionStatus {

    EMPTY_VALUE("CAFETERIA_1001", "갱신할 수 있는 값이 없습니다.");

    private final String code;
    private final String message;

    CafeteriaExceptionStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
