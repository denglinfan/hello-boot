package com.example.helloboot.designParttern.parttern.decorator.structs;

public class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void sampleOperation() {
        super.sampleOperation();
        //the details service code
    }
}
