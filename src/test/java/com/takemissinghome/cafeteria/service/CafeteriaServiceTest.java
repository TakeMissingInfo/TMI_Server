package com.takemissinghome.cafeteria.service;

import com.takemissinghome.cafeteria.api.request.CafeteriaRenewRequest;
import com.takemissinghome.cafeteria.api.response.CafeteriaOpenApiResponse.Item;
import com.takemissinghome.cafeteria.exception.CafeteriaException;
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
}
