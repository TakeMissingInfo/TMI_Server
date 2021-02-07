package com.takemissinghome.weakperson.service;

import com.takemissinghome.gov.response.Content;
import com.takemissinghome.weakperson.api.request.BenefitDataRequest;
import com.takemissinghome.weakperson.domain.BenefitData;
import com.takemissinghome.weakperson.domain.BenefitType;
import com.takemissinghome.weakperson.domain.WeakPerson;
import com.takemissinghome.weakperson.domain.WeakPersonType;
import com.takemissinghome.weakperson.exception.WeakPersonException;
import com.takemissinghome.weakperson.repository.WeakPersonRepository;
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
public class WeakPersonServiceTest {

    @Autowired
    WeakPersonRepository weakPersonRepository;

    @Autowired
    WeakPersonService weakPersonService;

    @DisplayName("사회적 약자 데이터를 갱신한다.")
    @Test
    public void renewDataTest() throws Exception {
        //given
        WeakPersonType weakPersonType = WeakPersonType.DISABLE;
        BenefitType benefitType = BenefitType.EMPLOYMENT;
        BenefitDataRequest benefitDataRequest = new BenefitDataRequest(new Content());

        //when
        weakPersonService.renewData(weakPersonType, benefitType, asList(benefitDataRequest));

        //then
        final WeakPerson actual = weakPersonRepository.findById(1L).get();
        assertThat(actual.getWeakPersonType()).isEqualTo(weakPersonType);
        assertThat(actual.getBenefitType()).isEqualTo(benefitType);
    }

    @DisplayName("사회적 약자 혜택 정보를 찾는다.")
    @Test
    public void findBenefitDataDetailsTest() throws Exception {
        //given
        String weakPersonName = "DISABLE";
        List<String> benefitNames = asList("EMPLOYMENT", "FINANCE");

        for (String benefitName : benefitNames) {
            final WeakPersonType weakPersonType = WeakPersonType.findByName(weakPersonName);
            final BenefitType benefitType = BenefitType.findByName(benefitName);
            final BenefitDataRequest benefitDataRequest = new BenefitDataRequest(new Content());
            weakPersonService.renewData(weakPersonType, benefitType, asList(benefitDataRequest));
        }

        //when
        final List<BenefitData> actual = weakPersonService.findBenefitDataDetails(weakPersonName, benefitNames);

        //then
        assertThat(actual.size()).isEqualTo(benefitNames.size());
    }

    @DisplayName("찾고자 하는 헤택 정보를 입력하지 않으면 IllegalArgumentException을 발생시킨다.")
    @Test
    public void findBenefitDataDetailsExceptionTest() throws Exception {
        //given
        String weakPersonName = "DISABLE";
        List<String> benefitNames = new ArrayList<>();

        //then
        assertThatThrownBy(() -> weakPersonService.findBenefitDataDetails(weakPersonName, benefitNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("benefits data is empty");
    }
}
