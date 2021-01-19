package com.takemissinghome.weakperson.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class BenefitData {

    @Column(name = "svc_id", unique = true)
    private String svcId;

    private String name;
    private String competentInstitution;
    private String supportForm;

    @Column(length = 1000)
    private String detailsInfo;
    private String detailsInfoUrl;

    public BenefitData(String svcId, String name, String competentInstitution,
                       String supportForm, String detailsInfo, String detailsInfoUrl) {
        this.svcId = svcId;
        this.name = name;
        this.competentInstitution = competentInstitution;
        this.supportForm = supportForm;
        this.detailsInfo = detailsInfo;
        this.detailsInfoUrl = detailsInfoUrl;
    }
}
