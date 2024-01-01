package com.gd.reservationservices.application.payment.value;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SerialNumberGeneratorTest {
    @DisplayName("고유번호 10개를 생성한다.")
    @Test
    void generateSerialNumber() {
        //given
        SerialNumberGenerator serialNumberGenerator = new SerialNumberGenerator(10);

        //when
        //then
        assertThat(serialNumberGenerator.getList()).hasSize(10);
    }
}
