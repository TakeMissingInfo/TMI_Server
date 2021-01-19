package com.takemissinghome.weakperson.api.dto.request;

import com.takemissinghome.gov.response.Content;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BenefitDataRequest {

    private String svcId;
    private String name;
    private String competentInstitution;
    private String supportForm;
    private String detailsInfo;
    private String detailsInfoUrl;

    public BenefitDataRequest(Content content) {
        this.svcId = content.getSvcId();
        this.name = content.getSvcNm();
        this.competentInstitution = content.getJrsdDptAllNm();
        this.supportForm = content.getSportFr();
        this.detailsInfo = content.getSvcPpo();
        this.detailsInfoUrl = content.getSvcInfoUrl();
    }
}
