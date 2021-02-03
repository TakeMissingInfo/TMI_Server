package com.takemissinghome.cafeteria.exception;

import lombok.Getter;

@Getter
public class CafeteriaException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    public CafeteriaException(String errorMessage, String errorCode, String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public CafeteriaException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public CafeteriaException(CafeteriaExceptionStatus exceptionStatus, String detailMessage) {
        super(detailMessage);
        this.errorCode = exceptionStatus.getCode();
        this.errorMessage = exceptionStatus.getMessage();
    }
}
