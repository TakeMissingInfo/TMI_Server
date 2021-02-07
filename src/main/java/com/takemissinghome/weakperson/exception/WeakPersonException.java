package com.takemissinghome.weakperson.exception;

import lombok.Getter;

@Getter
public class WeakPersonException extends RuntimeException {

    private String errorMessage;
    private String errorCode;

    public WeakPersonException(String errorMessage, String errorCode, String detailMessage) {
        super(detailMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public WeakPersonException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public WeakPersonException(WeakPersonStatusException statusException, String detailMessage) {
        super(detailMessage);
        this.errorMessage = statusException.getMessage();
        this.errorCode = statusException.getCode();
    }
}
