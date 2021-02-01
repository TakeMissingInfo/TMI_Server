package com.takemissinghome.cafeteria.service;

import com.takemissinghome.cafeteria.api.response.CafeteriaOpenApiResponse.Item;
import com.takemissinghome.cafeteria.model.Cafeteria;
import com.takemissinghome.cafeteria.model.Location;
import com.takemissinghome.cafeteria.repository.CafeteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CafeteriaService {

    private final CafeteriaRepository cafeteriaRepository;

    @Transactional
    public void renew(List<Item> items) {
        final List<Cafeteria> cafeterias = items.stream()
                .map(item -> new Cafeteria(item.getFcltyNm(), item.getRdnmadr(), item.getPhoneNumber(),
                        item.getMlsvTime(), item.getMlsvDate(), new Location(item.getLatitude(), item.getLongitude())))
                .collect(toList());

        cafeteriaRepository.saveAll(cafeterias);
    }
}
