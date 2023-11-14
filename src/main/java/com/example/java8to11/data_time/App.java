package com.example.java8to11.data_time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class App {
    public static void main(String[] args) throws InterruptedException {
        //<---------------기존에 있는 시간, 날짜 관련 package------------------------>
        // 이름이 작명이 잘 되어있지 않다.
        Date date = new Date();
        //날짜에서 시간을 가져온다? -> 게다가 EPOCK Time 즉 기계용시간이다.
        long time = date.getTime();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        Thread.sleep(3000);
        //Date클래스는 시간이 변한다.
        Date after3Seconds = new Date();
        System.out.println(after3Seconds);
        after3Seconds.setTime(time);
        System.out.println(after3Seconds);
        //같은 Instance인데 Instance안에 있는 데이터가 바뀐것 -> Mutable하다.
        //Mutable한 객체는 Multi-Thread환경에서 안전하게 사용하기가 어렵다.
        //T1의 작업중에 T2가 작업하면, Mutable한 경우에 Thread-Safe하지 않다.

        //Month는 0부터 시작한다. -> 헷갈린다. -> Type Safety가 보장이 되지 않는다. -> -10이렇게 들어올 수도 있다.
        Calendar gangminBirthDay= new GregorianCalendar(2000, Calendar.NOVEMBER, 11);
        //Calendar에서 getTime하면 또 Date가 튀어나온다.
        System.out.println(gangminBirthDay.getTime());
        gangminBirthDay.add(Calendar.DAY_OF_YEAR, 3);
        System.out.println(gangminBirthDay.getTime());

        //초단위 - Duration, 날짜 단위 - Period
    }
}
