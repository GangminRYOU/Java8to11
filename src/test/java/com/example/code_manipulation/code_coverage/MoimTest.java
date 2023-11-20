package com.example.code_manipulation.code_coverage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoimTest {
    @Test
    @DisplayName("모임인원이 꽉 찼는지")
    void isFull() throws Exception {
        //given
        Moim moim = new Moim();
        moim.maxNumberOfAttendees = 100;
        moim.numberOfEnrollment = 10;
        //when & then
        Assertions.assertFalse(moim.isEnrollmentFull());

    }

}