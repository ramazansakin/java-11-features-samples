package com.rsakin.java11.string;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

// String new methods sample code blocks
@Slf4j
public class Example {

    private static void writeHeader(final String headerText) {
        final String headerSeparator = "=".repeat(headerText.length() + 4);
        log.info("Header:");
        log.info("\n" + headerSeparator);
        log.info("\n" + headerText);
        log.info("\n" + headerSeparator);
    }

    public static void demonstrateStringLines() {
        String originalString = "Hello-World-Hi";
        String stringWithoutLineSeparators = originalString.replace("-", "\\\\n");

        writeHeader("String.lines() on '" + stringWithoutLineSeparators + "'");
        originalString.lines().forEach(log::info);
    }

    public static void demonstrateStringStrip() {
        String originalString = "    test      test   ";

        writeHeader("String.strip() on '" + originalString + "'");
        log.info("'" + originalString.strip() + "'");
    }

    public static void demonstrateStringStripLeading() {
        String originalString = "   test test    ";

        writeHeader("String.stripLeading() on '" + originalString + "'");
        log.info("'" + originalString.stripLeading() + "'");
    }

    public static void demonstrateStringStripTrailing() {
        String originalString = "  test- test    test  ";

        writeHeader("String.stripTrailing() on '" + originalString + "'");
        log.info("'" + originalString.stripTrailing() + "'");
    }

    public static void demonstrateStringIsBlank() {
        writeHeader("String.isBlank()");

        String emptyString = "";
        log.info("Empty string is blank : " + emptyString.isBlank());

        String onlyLineSeparator = System.getProperty("line.separator");
        log.info("line separator char is blank : " + onlyLineSeparator.isBlank());

        String tabOnly = "\t";
        log.info("Tab char is blank : " + tabOnly.isBlank());

        String spacesOnly = "   ";
        log.info("Empty string with multiple space char is blank : " + spacesOnly.isBlank());

        // We can try more cases depends on our usage ...
    }

    public static void lines() {
        writeHeader("String.lines()");

        String str = "Hello \nWorld, I'm\nRamazan.";
        log.info(str.lines().collect(Collectors.toList()).toString());
    }

    // test all methods
    public static void main(String[] args) {
        writeHeader("User-Agent\tMozilla/5.0 \t(Macintosh; Intel Mac OS X 10_13_5)");
        demonstrateStringLines();
        demonstrateStringStrip();
        demonstrateStringStripLeading();
        demonstrateStringStripTrailing();
        demonstrateStringIsBlank();
        lines();
    }

}
