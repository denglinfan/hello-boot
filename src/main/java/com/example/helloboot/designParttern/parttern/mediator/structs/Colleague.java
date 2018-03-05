package com.example.helloboot.designParttern.parttern.mediator.structs;

public abstract class Colleague {

    private Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator(){
        return mediator;
    }
}
