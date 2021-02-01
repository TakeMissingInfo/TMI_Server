package com.takemissinghome.cafeteria.api;

import com.takemissinghome.cafeteria.api.response.CafeteriaOpenApiResponse;
import com.takemissinghome.cafeteria.service.CafeteriaOpenApiService;
import com.takemissinghome.cafeteria.service.CafeteriaService;
import com.takemissinghome.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.takemissinghome.utils.DefaultResponse.res;
import static com.takemissinghome.utils.ResponseMessage.*;
import static com.takemissinghome.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/cafeteria")
@RequiredArgsConstructor
public class CafeteriaController {

    private final CafeteriaOpenApiService cafeteriaOpenApiService;
    private final CafeteriaService cafeteriaService;

    @PostMapping("/renew")
    public DefaultResponse renew(){
        try {
            final CafeteriaOpenApiResponse cafeteriaResponse = cafeteriaOpenApiService.getCafeteriaResponse();
            cafeteriaService.renew(cafeteriaResponse.getBody().getItems());

            return res(OK, RENEW_CAFETERIA);
        }catch (Exception e){
            log.error(e.getMessage());
            return res(BAD_REQUEST, RENEW_CAFETERIA_FAIL);
        }
    }
}
