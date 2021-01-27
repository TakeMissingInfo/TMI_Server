package com.takemissinghome.cafeteria.service;

import com.takemissinghome.cafeteria.domain.FreeCafeteriaResponse;
import com.takemissinghome.cafeteria.property.FreeCafeteriaProperty;
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
    private final FreeCafeteriaProperty freeCafeteriaProperty;

    public FreeCafeteriaResponse findCafeteriaInfo() throws IOException {

        ResponseEntity<FreeCafeteriaResponse> response = restTemplate.getForEntity(
                freeCafeteriaProperty.getUrl() + freeCafeteriaProperty.getKey(), FreeCafeteriaResponse.class);

        return response.getBody();
    }
}
