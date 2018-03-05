package com.example.helloboot.designParttern.parttern.mediator;

public abstract class Colleagues {

    private Mediators mediators;

    public Colleagues(Mediators mediators) {
        this.mediators = mediators;
    }

    public Mediators getMediator(){
        return mediators;
    }
}
