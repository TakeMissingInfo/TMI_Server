package com.takemissinghome.gov.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@NoArgsConstructor
@XmlRootElement(name = "svc")
@XmlAccessorType(XmlAccessType.FIELD)
public class Content {
    private String svcId;

    // 혜택 제목
    private String svcNm;

    // 소관 기관
    private String jrsdDptAllNm;

    // 혜택 상세 내용
    private String svcPpo;

    // 지원 형태
    private String sportFr;

    // 혜택 상세 정보 URL
    private String svcInfoUrl;
}
