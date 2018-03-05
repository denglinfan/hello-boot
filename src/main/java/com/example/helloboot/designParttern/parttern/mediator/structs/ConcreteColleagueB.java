package com.example.helloboot.designParttern.parttern.mediator.structs;

public class ConcreteColleagueB extends Colleague {

    public ConcreteColleagueB(Mediator mediator) {
        super(mediator);
    }

    public void operation(){
        getMediator().changed(this);
    }
}
