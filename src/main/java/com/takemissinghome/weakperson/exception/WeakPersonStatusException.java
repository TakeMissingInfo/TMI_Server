package com.takemissinghome.weakperson.exception;

import lombok.Getter;

@Getter
public enum WeakPersonStatusException {

    INVALID_WEAK_PERSON_TYPE("WEAK_PERSON_1001", "등록되어 있지 않은 사회적 약자입니다."),
    INVALID_WEAK_PERSON_TYPE_CODE("WEAK_PERSON_1002", "등록되어 있지 않은 사회적 약자 코드입니다."),
    INVALID_BENEFIT_TYPE("WEAK_PERSON_BENEFIT_1003", "등록되어 있지 않은 사회적 약자 혜택입니다."),
    INVALID_BENEFIT_TYPE_CODE("WEAK_PERSON_BENEFIT_1004", "등록되어 있지 않은 사회적 약자 혜택 코드입니다.");

    private String code;
    private String message;

    WeakPersonStatusException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
