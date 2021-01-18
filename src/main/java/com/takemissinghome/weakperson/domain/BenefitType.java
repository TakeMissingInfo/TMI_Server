package com.takemissinghome.weakperson.domain;

public enum BenefitType {
    EMPLOYMENT("취업/직장", "010000"),
    FINANCE("금융/세금/법률", "030000"),
    LIFE("생활/병역,","040000"),
    MEDICAL_CARE("건강/의료/사망", "050000"),
    MARRIAGE_PARENTING("결혼/육아/교육", "060000"),
    ENVIRONMENTAL_DISASTER("환경/재난", "070000"),
    HOUSING("주택/부동산", "090000"),
    MOTOR_TRAFFIC("자동차/교통", "100000");

    private String token;
    private String code;

    BenefitType(String token, String code) {
        this.token = token;
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
