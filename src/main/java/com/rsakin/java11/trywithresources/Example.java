package com.rsakin.java11.trywithresources;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;

// Try with resources test
@Slf4j
public class Example {

    public static void main(String[] args) throws Exception {
        BufferedReader reader1 = new BufferedReader(new FileReader("./README.md"));
        // try with no catch
        try (reader1) {
            while (reader1.ready()) {
                log.info(reader1.readLine());
            }
        }
    }

}
