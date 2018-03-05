package com.example.helloboot.designParttern.parttern.observer;

public class ConcreteSubject extends Subject {

    private String state;

    public String getState() {
        return state;
    }

    public void change(String newState){
        this.state = newState;

        System.out.println("主题目前的状态为：" + getState());

        //状态发生变化，调用方法通知所有的观察者
        this.notifyObserver(state);
    }

    public void changePull(String newState){
        this.state = newState;
        System.out.println("主题目前的状态为：" + getState());

        this.notifyObserver();
    }
}
