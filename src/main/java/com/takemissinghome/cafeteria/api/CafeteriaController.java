package com.takemissinghome.cafeteria.api;

import com.takemissinghome.cafeteria.api.request.CafeteriaRenewRequest;
import com.takemissinghome.cafeteria.api.response.CafeteriaDetailsResponse;
import com.takemissinghome.cafeteria.api.response.CafeteriaOpenApiResponse;
import com.takemissinghome.cafeteria.model.Cafeteria;
import com.takemissinghome.cafeteria.service.CafeteriaOpenApiService;
import com.takemissinghome.cafeteria.service.CafeteriaService;
import com.takemissinghome.utils.DefaultResponse;
import com.takemissinghome.weakperson.exception.WeakPersonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.takemissinghome.cafeteria.api.response.CafeteriaOpenApiResponse.*;
import static com.takemissinghome.utils.DefaultResponse.res;
import static com.takemissinghome.utils.ResponseMessage.*;
import static com.takemissinghome.utils.StatusCode.*;
import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequestMapping("/api/v1/cafeteria")
@RequiredArgsConstructor
public class CafeteriaController {

    private final CafeteriaOpenApiService cafeteriaOpenApiService;
    private final CafeteriaService cafeteriaService;

    @PostMapping("/renew")
    public DefaultResponse renew() {
        try {
            final CafeteriaOpenApiResponse cafeteriaResponse = cafeteriaOpenApiService.getCafeteriaResponse();
            cafeteriaService.renew(toCafeteriasDetailsRequest(cafeteriaResponse.getBody().getItems()));

            return res(OK, RENEW_CAFETERIA);
        } catch (WeakPersonException e) {
            log.error(e.getMessage());
            return res(BAD_REQUEST, RENEW_CAFETERIA_FAIL);
        }
    }

    @GetMapping("/{latitude}/{longitude}")
    public DefaultResponse<List<CafeteriaDetailsResponse>> showCafeteriasDetailsNearby(
            @PathVariable(value = "latitude") double latitude,
            @PathVariable(value = "longitude") double longitude) {
        try {
            List<Cafeteria> cafeterias = cafeteriaService.showCafeteriasNearby(latitude, longitude);
            return res(OK, FIND_CAFETERIA, toCafeteriasDetailsResponse(cafeterias));
        } catch (Exception e) {
            log.error(e.getMessage());
            return res(NOT_FOUND, NOT_FOUND_CAFETERIA);
        }
    }

    private List<CafeteriaRenewRequest> toCafeteriasDetailsRequest(List<Item> items) {
        return items.stream()
                .map(CafeteriaRenewRequest::new)
                .collect(toList());
    }

    private List<CafeteriaDetailsResponse> toCafeteriasDetailsResponse(List<Cafeteria> cafeterias) {
        return cafeterias.stream()
                .map(CafeteriaDetailsResponse::new)
                .collect(toList());
    }
}
