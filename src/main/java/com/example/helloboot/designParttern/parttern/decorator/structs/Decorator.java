package com.example.helloboot.designParttern.parttern.decorator.structs;

public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    public void sampleOperation() {
        component.sampleOperation();
    }
}
