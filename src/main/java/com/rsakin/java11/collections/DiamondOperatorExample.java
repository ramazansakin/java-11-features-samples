package com.rsakin.java11.collections;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
public class DiamondOperatorExample {

    @Data
    abstract static class MyHandler<T extends Serializable> {

        private T content;

        public MyHandler(T content) {
            this.content = content;
            log.info("Content : " + content.toString());
        }

        abstract void handle();
    }

    public static void main(String[] args) {

        MyHandler<Integer> intHandler = new MyHandler<>(1) {
            @Override
            public void handle() {
                log.info("Content : " + getContent());
            }
        };
        intHandler.handle();

        // Seperator
        log.info("###----------------------------------------------------###");

        MyHandler<? extends Integer> intHandler1 = new MyHandler<>(10) {
            @Override
            void handle() {
                log.info("Received content : " + getContent());
            }
        };
        intHandler1.handle();

        log.info("###----------------------------------------------------###");

        MyHandler<?> handler = new MyHandler<>("Sample Content") {
            @Override
            void handle() {
                log.info("Handle content : " + getContent());
            }
        };
        handler.handle();

    }

}
