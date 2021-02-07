package com.takemissinghome.weakperson.api;

import com.takemissinghome.gov.response.Content;
import com.takemissinghome.gov.response.ResponseModel;
import com.takemissinghome.gov.service.OpenApiService;
import com.takemissinghome.utils.DefaultResponse;
import com.takemissinghome.weakperson.api.request.BenefitDataRequest;
import com.takemissinghome.weakperson.api.request.RenewRequest;
import com.takemissinghome.weakperson.api.response.BenefitDataResponse;
import com.takemissinghome.weakperson.domain.BenefitData;
import com.takemissinghome.weakperson.domain.BenefitType;
import com.takemissinghome.weakperson.domain.WeakPersonType;
import com.takemissinghome.weakperson.exception.WeakPersonException;
import com.takemissinghome.weakperson.service.WeakPersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.takemissinghome.utils.DefaultResponse.res;
import static com.takemissinghome.utils.ResponseMessage.*;
import static com.takemissinghome.utils.StatusCode.*;
import static java.util.stream.Collectors.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/weakperson")
@RequiredArgsConstructor
public class WeakPersonController {

    private final WeakPersonService weakPersonService;
    private final OpenApiService openApiService;

    @PostMapping("/renew")
    public DefaultResponse<Integer> renewWeakPersonData(@RequestBody RenewRequest renewRequest) {
        try {
            final WeakPersonType weakPersonType = WeakPersonType.findByName(renewRequest.getWeakPersonType());
            final BenefitType benefitType = BenefitType.findByName(renewRequest.getBenefitType());

            final ResponseModel responseModel = openApiService.getBenefitDataOfWeakPerson(
                    weakPersonType.getCode(), benefitType.getCode());
            final Integer renewSize = weakPersonService.renewData(weakPersonType, benefitType, toBenefitDataList(responseModel));

            return res(OK, RENEW_WEAK_PERSON, renewSize);
        } catch (WeakPersonException e) {
            log.error(e.getMessage());
            return res(BAD_REQUEST, RENEW_WEAK_PERSON_FAIL);
        }
    }

    @GetMapping("/{type}")
    public DefaultResponse<List<BenefitDataResponse>> showBenefitDataDetails(@PathVariable(value = "type") String weakPersonType,
                                                                             @RequestParam(value = "benefitType") List<String> benefitTypes) {
        try {
            final List<BenefitData> benefitDataList = weakPersonService.findBenefitDataDetails(weakPersonType, benefitTypes);
            final List<BenefitDataResponse> benefitDataResponses = convertToBenefitDataResponse(benefitDataList);

            return res(OK, FIND_BENEFIT_DATA, benefitDataResponses);
        } catch (WeakPersonException e) {
            log.error(e.getMessage());
            return res(NO_CONTENT, NOT_FOUND_BENEFIT_DATA);
        }
    }

    private List<BenefitDataRequest> toBenefitDataList(ResponseModel responseModel) {
        final List<Content> contents = responseModel.getContents();
        return contents.stream()
                .map(BenefitDataRequest::new)
                .collect(toList());
    }

    private List<BenefitDataResponse> convertToBenefitDataResponse(List<BenefitData> benefitDataList) {
        return benefitDataList.stream()
                .map(BenefitDataResponse::new)
                .collect(toList());
    }
}
