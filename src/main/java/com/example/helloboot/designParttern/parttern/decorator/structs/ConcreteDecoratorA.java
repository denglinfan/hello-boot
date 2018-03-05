package com.example.helloboot.designParttern.parttern.decorator.structs;

public class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void sampleOperation() {
        super.sampleOperation();
        //the details service code
    }
}
