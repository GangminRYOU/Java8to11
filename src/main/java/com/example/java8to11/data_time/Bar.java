package com.example.java8to11.data_time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bar {
    public static void main(String[] args) {
        //Period는 Human용 <-> Duration은 Machine용
        log.info("Duration활용");
        Instant instant = Instant.now();
        Instant plus = instant.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(instant, plus);
        log.info("기계용시간 비교 Duration - between={}", between);


        //Formatting
        log.info("문자열로 입력을 받거나, LocalDateTime을 문자열로 출력하는 경우");
        LocalDateTime now = LocalDateTime.now();
        log.info("그냥 출력 now={}", now);
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        log.info("DateTimeFormatter활용 DateTimeFormatter.ofPattern={}", now.format(MMddyyyy));

        log.info("문자열을 날짜 Type으로 Parsing");
        LocalDate parse = LocalDate.parse("07/05/1982", MMddyyyy);
        log.info("parsed data={}", parse);



        //Legacy API 지원
        Date date = new Date();
        log.info("date to instant");
        Instant fluent = date.toInstant();
        log.info("instant to date");
        Date newDate = Date.from(fluent);

        log.info("GregorianCalender to LocalDateTime");
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        LocalDateTime localDateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        ZonedDateTime zonedDateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
        log.info("LocalDateTime to GregorianCalendar");
        GregorianCalendar legacy = GregorianCalendar.from(zonedDateTime);
        log.info("legacy={}", legacy);

        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone legacyZone = TimeZone.getTimeZone(zoneId);

        log.info("연산하기 편한 방법");
        LocalDateTime newAPI = localDateTime.plus(10, ChronoUnit.DAYS);
        log.info("새로운 API는 인스턴스를 만들어야 연산이 적용된다. Immutable하기 때문에");
        log.info("newAPI={}", newAPI);
    }
}
