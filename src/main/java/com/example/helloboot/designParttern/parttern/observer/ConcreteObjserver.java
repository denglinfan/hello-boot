package com.example.helloboot.designParttern.parttern.observer;

public class ConcreteObjserver implements Observer {

    public String state;

    public void update(String state) {
        this.state = state;
        System.out.println("状态发生变化："  + this.state);
    }

    public void update(Subject subject) {
        this.state = ((ConcreteSubject) subject).getState();

        System.out.println("状态发生变化：" + this.state);
    }
}
