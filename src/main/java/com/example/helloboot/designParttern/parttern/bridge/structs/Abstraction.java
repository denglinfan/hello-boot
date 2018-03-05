package com.example.helloboot.designParttern.parttern.bridge.structs;

public abstract class Abstraction {

    protected Implementor impl;

    public Abstraction(Implementor impl) {
        this.impl = impl;
    }

    public void operation(){
        impl.operationImpl();
    }
}
