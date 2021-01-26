package com.takemissinghome.cafeteria.service;

import com.takemissinghome.cafeteria.domain.FreeCafeteriaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class FreeCafeteriaService {

    private final RestTemplate restTemplate;

    private final Resource serviceKeyFile = new ClassPathResource("openapi/cafeteria_service_key");
    private String freeCafeteriaPath = "http://api.data.go.kr/openapi/tn_pubr_public_free_mlsv_api?pageNo=0&numOfRows=100&type=json&serviceKey=";

    public FreeCafeteriaResponse findCafeteriaInfo() throws IOException {
        final String serviceKey = Files.readAllLines(serviceKeyFile.getFile().toPath()).get(0);
        freeCafeteriaPath += serviceKey;

        ResponseEntity<FreeCafeteriaResponse> response = restTemplate.getForEntity(
                freeCafeteriaPath, FreeCafeteriaResponse.class);

        return response.getBody();
    }
}
