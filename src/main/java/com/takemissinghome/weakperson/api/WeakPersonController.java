package com.takemissinghome.weakperson.api;

import com.takemissinghome.cafeteria.api.response.CafeteriaDetailsResponse;
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
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.takemissinghome.utils.DefaultResponse.res;
import static com.takemissinghome.utils.ResponseMessage.*;
import static com.takemissinghome.utils.StatusCode.*;
import static java.util.stream.Collectors.*;

@Api(tags = "WeakPerson")
@Slf4j
@RestController
@RequestMapping("/api/v1/weakperson")
@RequiredArgsConstructor
public class WeakPersonController {

    private final WeakPersonService weakPersonService;
    private final OpenApiService openApiService;

    @ApiOperation(value = "Renew weak person data", notes = "사회적 약자 데이터 갱신")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사회적 약자 혜택 정보 갱신 성공"),
            @ApiResponse(code = 400, message = "사회적 약자 혜택 정보 갱신 실패")})
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

    @ApiOperation(value = "Show benefit data by weak person type and benefit types", notes = "사회적 약자 유형 및 혜택 유형별 사회적 약자 혜택 정보 보여주기",
            response = BenefitDataResponse.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "혜택 정보 찾기 성공"),
            @ApiResponse(code = 404, message = "혜택 정보 찾기 실패")})
    @GetMapping("/{type}")
    public DefaultResponse<List<BenefitDataResponse>> showBenefitDataDetails(
            @ApiParam(name = "사회적 약자 타입", required = true) @PathVariable(value = "type") String weakPersonType,
            @ApiParam(name = "찾고자 하는 혜택 유형들", required = true) @RequestParam(value = "benefitType") List<String> benefitTypes) {
        try {
            final List<BenefitData> benefitDataList = weakPersonService.findBenefitDataDetails(weakPersonType, benefitTypes);
            final List<BenefitDataResponse> benefitDataResponses = convertToBenefitDataResponse(benefitDataList);

            return res(OK, FIND_BENEFIT_DATA, benefitDataResponses);
        } catch (WeakPersonException e) {
            log.error(e.getMessage());
            return res(NOT_FOUND, NOT_FOUND_BENEFIT_DATA);
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
