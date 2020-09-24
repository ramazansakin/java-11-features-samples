package com.rsakin.java11.var;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// var usages
@Slf4j
public class Example {

    public static void main(String[] args) throws Exception {
        // sample var typed variables
        var list = new ArrayList<String>();
        var testStreamVar = list.stream();

        var newList = List.of("Hello", "World");
        newList.forEach(log::info);

        String fileName = "./pom.xml";
        var path = Paths.get(fileName);
        var bytes = Files.readAllBytes(path);

        log.info(String.format("Byte Array : %s", bytes));

        // var in for each
        for (var b : bytes) {
            log.info(String.valueOf(b));
        }

        // var in try-catch
        try (var foo = new FileInputStream(new File(""))) {
            log.info(String.valueOf(foo));
        } catch (Exception e) {
            // ignore
        }

        // var in lambda
        var arrInteger = new Integer[]{5, 9, 3, 6, 2, 4, 8, 7, 1};
        long cnt = Arrays.stream(arrInteger).filter(
                (var a) -> (a != null && a > 5)).count();

        log.info(String.valueOf(cnt));

    }
}
