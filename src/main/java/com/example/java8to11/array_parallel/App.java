package com.example.java8to11.array_parallel;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        int size = 1500;
        int[] numbers = new int[size];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        long start = System.nanoTime();
        Arrays.sort(numbers);
        log.info("serial sorting took: {}", System.nanoTime() - start);

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers);
        log.info("parallel sorting took: {}", System.nanoTime() - start);
    }
}
