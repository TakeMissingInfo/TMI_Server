package com.takemissinghome.weakperson.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class BenefitData {
    private String name;
    private String competentInstitution;
    private String supportForm;
    private String detailsInfo;
    private String detailsInfoUrl;
}
