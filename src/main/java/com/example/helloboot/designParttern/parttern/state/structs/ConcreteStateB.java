package com.example.helloboot.designParttern.parttern.state.structs;

public class ConcreteStateB implements State {

    public void handle(String sampleParameter) {
        System.out.println("ConcreteStateB handle: " +sampleParameter);
    }
}
