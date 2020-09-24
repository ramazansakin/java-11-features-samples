package com.rsakin.java11.time;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

// TimeUnit test
@Slf4j
public class Example {

    public static void main(String[] args) {
        long day = TimeUnit.DAYS.convert(Duration.ofHours(24));
        log.info(1 == day ? "TRUE" : "FALSE");

        log.info(String.valueOf(TimeUnit.DAYS.convert(Duration.ofHours(26))));

        log.info(String.valueOf(TimeUnit.MINUTES.convert(Duration.ofSeconds(60))));
    }

}
