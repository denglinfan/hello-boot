package com.example.helloboot.designParttern.parttern.mediator.structs;

public class ConcreteColleagueA extends Colleague {

    public ConcreteColleagueA(Mediator mediator) {
        super(mediator);
    }

    public void operation(){
        getMediator().changed(this);
    }
}
