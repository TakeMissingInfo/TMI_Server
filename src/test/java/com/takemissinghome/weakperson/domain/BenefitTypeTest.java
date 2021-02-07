package com.takemissinghome.weakperson.domain;

import com.takemissinghome.weakperson.exception.WeakPersonException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BenefitTypeTest {

    @DisplayName("코드 값으로 BenefitType을 찾는다.")
    @ParameterizedTest
    @CsvSource(value = {"010000, EMPLOYMENT", "040000, LIFE", "060000, MARRIAGE_PARENTING"})
    public void findByCodeTest(String code, BenefitType expected) throws Exception {
        //given
        final BenefitType actual = BenefitType.findByCode(code);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("찾을 수 없는 BenefitType 코드일 경우 WeakPersonException을 발생시킨다.")
    @Test
    public void findByCodeExceptionTest() throws Exception {
        //given
        String notRegisteredCode = "not register";

        //then
        assertThatThrownBy(() -> BenefitType.findByCode(notRegisteredCode))
                .isInstanceOf(WeakPersonException.class)
                .hasMessage(String.format("input code: %s, not found benefit type", notRegisteredCode));
    }

    @DisplayName("BenefitType String 값으로 WeakPersonType을 찾는다.")
    @ParameterizedTest
    @CsvSource(value = {"EMPLOYMENT, EMPLOYMENT", "FINANCE, FINANCE", "HOUSING, HOUSING"})
    public void findByNameTest(String name, BenefitType expected) throws Exception {
        //given
        final BenefitType actual = BenefitType.findByName(name);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("찾을 수 없는 BenefitType일 경우 WeakPersonException을 발생시킨다.")
    @Test
    public void findByNameExceptionTest() throws Exception {
        //given
        String notFoundName = "BENEFIT_TYPE_TEST";

        //then
        assertThatThrownBy(() -> BenefitType.findByName(notFoundName))
                .isInstanceOf(WeakPersonException.class)
                .hasMessage(String.format("input value: %s, not found benefit type", notFoundName));
    }
}
