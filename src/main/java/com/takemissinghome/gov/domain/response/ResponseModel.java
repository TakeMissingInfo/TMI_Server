package com.takemissinghome.gov.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@NoArgsConstructor
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseModel {
    private int resultCode;
    private String resultMessage;
    private int totalCount;
    private int pageSize;
    private int pageIndex;

    @XmlElementWrapper(name = "svcList")
    @XmlElement(name = "svc")
    private List<Content> contents;
}


