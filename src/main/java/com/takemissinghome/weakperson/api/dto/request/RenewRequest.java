package com.takemissinghome.weakperson.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RenewRequest {
    private String benefitCode;
    private String weakPersonCode;
}
