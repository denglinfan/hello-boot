package com.example.helloboot.designParttern.parttern.mediator.structs;

public class ConcreteMediator implements Mediator {

    private ConcreteColleagueA colleagueA;

    private ConcreteColleagueB colleagueB;

    public ConcreteMediator(ConcreteColleagueA colleagueA) {
        this.colleagueA = colleagueA;
    }

    public ConcreteMediator(ConcreteColleagueB colleagueB) {
        this.colleagueB = colleagueB;
    }

    public void changed(Colleague c) {
        /**
         * 某一个同事类发生了变化，通常需要与其他同事交互
         * 具体协调相应的同事对象来实现协作行为
         */
    }
}
