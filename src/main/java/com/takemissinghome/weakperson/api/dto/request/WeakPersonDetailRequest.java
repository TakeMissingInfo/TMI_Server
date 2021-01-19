package com.takemissinghome.weakperson.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class WeakPersonDetailRequest {

    private String benefitCode;
    private String weakPersonCode;
    private List<BenefitDataRequest> benefitDataRequests;

    public WeakPersonDetailRequest(String benefitCode, String weakPersonCode, List<BenefitDataRequest> benefitDataRequests) {
        this.benefitCode = benefitCode;
        this.weakPersonCode = weakPersonCode;
        this.benefitDataRequests = benefitDataRequests;
    }
}
