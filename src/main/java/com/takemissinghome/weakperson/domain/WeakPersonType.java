package com.takemissinghome.weakperson.domain;

import java.util.Arrays;

public enum WeakPersonType {
    DISABLE("%EC%9E%A5%EC%95%A0%EC%9D%B8"),
    OLD_MAN("%EB%85%B8%EC%9D%B8"),
    LOW_INCOME("%EC%A0%80%EC%86%8C%EB%93%9D%EC%B8%B5"),
    BOYS_GIRLS_FAMILY("%EC%86%8C%EB%85%84%EC%86%8C%EB%85%80%EA%B0%80%EC%9E%A5");

    private String code;

    WeakPersonType(String code) {
        this.code = code;
    }

    public static WeakPersonType findByCode(String code) {
        return Arrays.stream(values())
                .filter(weakPersonType -> weakPersonType.equalsCode(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("not found weakPerson type"));
    }

    private boolean equalsCode(String code) {
        return this.code.equals(code);
    }
}