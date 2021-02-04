package com.takemissinghome.cafeteria.service;

import com.takemissinghome.cafeteria.api.request.CafeteriaRenewRequest;
import com.takemissinghome.cafeteria.exception.CafeteriaException;
import com.takemissinghome.cafeteria.exception.CafeteriaExceptionStatus;
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
    public Integer renew(List<CafeteriaRenewRequest> cafeteriaRenewRequests) {
        checkEmpty(cafeteriaRenewRequests);
        final List<Cafeteria> cafeterias = cafeteriaRenewRequests.stream()
                .map(renewRequest -> new Cafeteria(renewRequest.getFacilityName(), renewRequest.getAddress(),
                        renewRequest.getPhoneNumber(), renewRequest.getOperatingTime(),
                        renewRequest.getOperatingDate(), new Location(renewRequest.getLatitude(), renewRequest.getLongitude())))
                .collect(toList());
        cafeteriaRepository.saveAll(cafeterias);

        return cafeterias.size();
    }

    @Transactional(readOnly = true)
    public List<Cafeteria> showCafeteriasNearby(double latitude, double longitude) {
        return cafeteriaRepository.findAllNearby(latitude, longitude);
    }

    private void checkEmpty(List<CafeteriaRenewRequest> items) {
        if (items.isEmpty()) {
            throw new CafeteriaException(CafeteriaExceptionStatus.EMPTY_VALUE, "renew data is empty");
        }
    }
}
