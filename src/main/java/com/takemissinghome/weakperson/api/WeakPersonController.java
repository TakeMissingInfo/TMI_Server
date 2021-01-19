package com.takemissinghome.weakperson.api;

import com.takemissinghome.gov.response.Content;
import com.takemissinghome.gov.response.ResponseModel;
import com.takemissinghome.gov.service.OpenApiService;
import com.takemissinghome.weakperson.api.dto.request.BenefitDataRequest;
import com.takemissinghome.weakperson.api.dto.request.RenewRequest;
import com.takemissinghome.weakperson.api.dto.request.WeakPersonDetailRequest;
import com.takemissinghome.weakperson.service.WeakPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/api/v1/weakperson")
@RequiredArgsConstructor
public class WeakPersonController {

    private final WeakPersonService weakPersonService;
    private final OpenApiService openApiService;

    @PostMapping("/renew")
    public ResponseEntity<String> renewWeakPersonData(@RequestBody RenewRequest renewRequest) {
        try {
            String benefitCode = renewRequest.getBenefitCode();
            String weakPersonCode = renewRequest.getWeakPersonCode();
            final ResponseModel responseModel = openApiService.getBenefitDataOfWeakPerson(benefitCode, weakPersonCode);

            weakPersonService.renewData(new WeakPersonDetailRequest(benefitCode, weakPersonCode, toBenefitDataList(responseModel)));
            return ResponseEntity.ok().body("success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private List<BenefitDataRequest> toBenefitDataList(ResponseModel responseModel) {
        final List<Content> contents = responseModel.getContents();
        return contents.stream()
                .map(BenefitDataRequest::new)
                .collect(toList());
    }
}
