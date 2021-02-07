package com.takemissinghome.cafeteria.service;

import com.takemissinghome.cafeteria.api.request.CafeteriaRenewRequest;
import com.takemissinghome.cafeteria.api.response.CafeteriaOpenApiResponse.Item;
import com.takemissinghome.cafeteria.exception.CafeteriaException;
import com.takemissinghome.cafeteria.model.Cafeteria;
import com.takemissinghome.cafeteria.model.Location;
import com.takemissinghome.cafeteria.repository.CafeteriaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CafeteriaServiceTest {

    @Autowired
    private CafeteriaService cafeteriaService;

    @Autowired
    private CafeteriaRepository cafeteriaRepository;

    @DisplayName("급식소 데이터를 갱신한다.")
    @Test
    public void renewTest() throws Exception {
        //given
        final CafeteriaRenewRequest cafeteriaRenewRequest = new CafeteriaRenewRequest(new Item());
        final List<CafeteriaRenewRequest> cafeteriaRenewRequests = asList(cafeteriaRenewRequest);

        //when
        final Integer renewSize = cafeteriaService.renew(cafeteriaRenewRequests);

        //then
        assertThat(renewSize).isEqualTo(cafeteriaRenewRequests.size());
    }

    @DisplayName("갱신할 데이터가 없을 시 WeakPersonException을 발생시킨다.")
    @Test
    public void renewExceptionTest() throws Exception {
        //given
        final List<CafeteriaRenewRequest> cafeteriaRenewRequests = new ArrayList<>();

        //then
        assertThatThrownBy(() -> cafeteriaService.renew(cafeteriaRenewRequests))
                .isInstanceOf(CafeteriaException.class)
                .hasMessage("renew data is empty");
    }

    @DisplayName("현재 위치 기준 가까운 순서대로 급식소를 조회한다.")
    @Test
    public void showCafeteriasNearby() throws Exception {
        //given
        final Cafeteria cafeteria = Cafeteria.builder()
                .facilityName("testName")
                .location(new Location(1L, 2L))
                .address("testAddress")
                .phoneNumber("010-1234-5678")
                .build();

        cafeteriaRepository.save(cafeteria);

        //when
        final List<Cafeteria> findCafeterias = cafeteriaService.showCafeteriasNearby(0L, 0L);

        //then
        assertThat(findCafeterias.size()).isEqualTo(1);
        assertThat(findCafeterias.get(0).getId()).isEqualTo(cafeteria.getId());
    }
}
