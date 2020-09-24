package com.rsakin.java11.collections;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;


@Slf4j
public class Example {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        // Immmutable list sample init
        List immutableList = List.of();

        // sample var usage local variables
        var foo = List.of("sample", "test", "code");
        log.info(foo.toString());

        // Seperator
        log.info("###----------------------------------------------------###");

        // Init of immutable map
        Map emptyImmutableMap = Map.of();

        //
        var map = Map.of(100, "sample", 4.5, "test");

        // Sample map init with static vars
        Map<Integer, String> entryMap = Map.ofEntries(
                entry(1, "test"),
                entry(2, "sample"),
                entry(3, "ok"),
                entry(4, "and"),
                entry(5, "code"),
                entry(10, "works")
        );
        log.info(entryMap.toString());

        // Seperator
        log.info("###----------------------------------------------------###");

        Map.Entry<String, String> immutableMapEntry = Map.entry("rsakin", "test");

        // we can copy entries easily
        Map<String, String> copyMap = Map.ofEntries(immutableMapEntry);

        // immutable set init
        Set<String> immutableSet = Set.of();

        // sample Set init
        Set<String> bar = Set.of("a", "b", "c", "d", "e", "f", "g");
        log.info(bar.toString());

    }

}
