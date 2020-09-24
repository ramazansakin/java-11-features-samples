package com.rsakin.java11.interfaces;

// java 11 sample interface
public interface Example {

    // sample static method interface and impl
    private static void sayHello() {
        System.out.println("Hello World!");
    }

    // normal interface unimplemented method
    void normalInterfaceMethod();

    // sample default method interface and impl
    default void interfaceMethodWithDefault() {
        init();
    }

    // another sample default method
    default void anotherDefaultMethod() {
        init();
    }

    // we can define private methods in interfaces from now on
    private void init() {
        System.out.println("Initializing..");
    }

}
