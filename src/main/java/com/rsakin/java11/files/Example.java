package com.rsakin.java11.files;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class Example {

    private static final String HELLO_TXT_FILENAME = "hello.txt";

    public static void main(String[] args) throws Exception {
        String text = "Hello World!";

        // Sample file
        Files.writeString(Paths.get(HELLO_TXT_FILENAME), text);

        // Get file content & compare
        String readText = Files.readString(Paths.get(HELLO_TXT_FILENAME));
        log.info("Texts are equal : " + text.equals(readText));

        // delete file
        Files.delete(Paths.get(HELLO_TXT_FILENAME));
    }

}