package com.takemissinghome.weakperson.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RenewRequest {
    private String weakPersonType;
    private String benefitType;
}
