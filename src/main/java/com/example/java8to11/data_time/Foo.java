package com.example.java8to11.data_time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Foo {
    public static void main(String[] args) {
        /*기계용 시간*/
        Instant instant = Instant.now();
        System.out.println(instant);
        //시간은 그리니치 시간 기준으로 UTC, GMT에서 온다.
        //특정 EPOCK Time 기준으로 만들려면
        Instant epock = Instant.ofEpochMilli(300);

        //나의 위치 기준 시간으로 변경하려면?
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        //시스템의 기준 시점
        System.out.println(zonedDateTime);
        System.out.println(instant.atZone(ZoneId.of("UTC")));


        /*사람용 시간*/
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        //이름처럼 Local이 붙어있기 떄문에 시스템 시간 기준으로 나타낸다.
        //만약 서버가 근데 미국에 있고, 그 서버에 프로그램이 배포된다면, 미국시간으로 찍히게 되는 것

        LocalDateTime helloWorld = LocalDateTime.of(1998, Month.APRIL, 15, 0, 0, 0);
        //특정 Zone의 시간을 보고싶다면?
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);

        Instant nownow = Instant.now();
        ZonedDateTime seoulZonedDateTime = nownow.atZone(ZoneId.of("Asia/Seoul"));
        log.info("Instant.atZone()={}", seoulZonedDateTime);



        /*기간을 비교하는 방법*/
        LocalDate today = LocalDate.now();
        LocalDate birthDay = LocalDate.of(2024, Month.JANUARY, 3);
        Period period = Period.between(today, birthDay);
        log.info("----------생일까지 몇일 남았는지 확인------------");
        System.out.println(period.getDays());
        Period until = today.until(birthDay);
        log.info("today.until(birthDay)={}", until);
        log.info("until.get(ChronoUnit.Days)=생일까지 {}일 남음", until.get(ChronoUnit.DAYS));
    }
}
