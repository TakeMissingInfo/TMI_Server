package com.takemissinghome.weakperson.domain;

import com.takemissinghome.weakperson.exception.WeakPersonException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WeakPersonTypeTest {

    @DisplayName("코드 값으로 WeakPesronType을 찾는다.")
    @ParameterizedTest
    @CsvSource(value = {"%EC%9E%A5%EC%95%A0%EC%9D%B8, DISABLE",
            "%EC%86%8C%EB%85%84%EC%86%8C%EB%85%80%EA%B0%80%EC%9E%A5, BOYS_GIRLS_FAMILY"})
    public void findByCodeTest(String code, WeakPersonType expected) throws Exception {
        //given
        final WeakPersonType actual = WeakPersonType.findByCode(code);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("찾을 수 없는 WeakPersonType의 코드일 경우 WeakPersonException을 발생시킨다.")
    @Test
    public void findByCodeExceptionTest() throws Exception {
        //given
        String notRegisteredCode = "not register";

        //then
        assertThatThrownBy(() -> WeakPersonType.findByCode(notRegisteredCode))
                .isInstanceOf(WeakPersonException.class)
                .hasMessage(String.format("input code: %s, not found weakPerson type", notRegisteredCode));
    }

    @DisplayName("WeakPesronType의 String 값으로 WeakPersonType을 찾는다.")
    @ParameterizedTest
    @CsvSource(value = {"DISABLE, DISABLE",
            "BOYS_GIRLS_FAMILY, BOYS_GIRLS_FAMILY"})
    public void findByNameTest(String name, WeakPersonType expected) throws Exception {
        //given
        final WeakPersonType actual = WeakPersonType.findByName(name);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("찾을 수 없는 WeakPersonType일 경우 WeakPersonException을 발생시킨다.")
    @Test
    public void findByNameExceptionTest() throws Exception {
        //given
        String notFoundName = "WEAK_PERSON_TEST";

        //then
        assertThatThrownBy(() -> WeakPersonType.findByName(notFoundName))
                .isInstanceOf(WeakPersonException.class)
                .hasMessage(String.format("input value: %s, not found weak person type", notFoundName));
    }
}
