package com.takemissinghome.gov.api;

import com.takemissinghome.gov.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OpenApiController {

    private final OpenApiService openApiService;

    @GetMapping("/test")
    public String getActualDealPrice() throws Exception {
        return openApiService.getDate();
    }
}
