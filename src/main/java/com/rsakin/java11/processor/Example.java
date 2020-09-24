package com.rsakin.java11.processor;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

// process class & basic methods test
@Slf4j
public class Example {

    public static void main(String[] args) {
        ProcessHandle currentProcess = ProcessHandle.current();
        log.info("Current Process ID : " + currentProcess.pid());

        // Get process info
        log.info("Current process info : " + currentProcess.info());

        // Get children process
        Stream<ProcessHandle> children = currentProcess.children();
        log.info("Children Processes : ");
        children.forEach(child -> log.info(child.info().toString()));

    }

}
