package com.takemissinghome.weakperson.api.response;

import com.takemissinghome.weakperson.domain.BenefitData;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BenefitDataResponse {

    private String name;
    private String competentInstitution;
    private String supportForm;
    private String detailsInfo;
    private String detailsInfoUrl;

    public BenefitDataResponse(BenefitData benefitData) {
        this.name = benefitData.getName();
        this.competentInstitution = benefitData.getCompetentInstitution();
        this.supportForm = benefitData.getSupportForm();
        this.detailsInfo = benefitData.getDetailsInfo();
        this.detailsInfoUrl = benefitData.getDetailsInfoUrl();
    }
}
