package com.example.code_manipulation.code_coverage;

public class Moim {
    int maxNumberOfAttendees;
    int numberOfEnrollment;

    /**
     * @return : 현재 신청이 꽉 찼는지 여부
     */
    public boolean isEnrollmentFull(){
        /**
         * 무한대로 받는 경우
         */
        if(maxNumberOfAttendees == 0){
            return false;
        }
        if(numberOfEnrollment < maxNumberOfAttendees){
            return false;
        }
        return true;
    }
}
