package com.example.helloboot.designParttern.parttern.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    /**
     * 存放所有注册的观察者
     */
    List<Observer> observers = new ArrayList<Observer>();

    /**
     * 添加注册观察者到集合中
     * @param observer
     */
    public void addObserver(Observer observer){
        if(observer == null){
            throw new NullPointerException();
        }

        if(!observers.contains(observer)){
            synchronized (this){
                if(!observers.contains(observer)){
                    observers.add(observer);
                }
            }
        }
    }

    /**
     * 删除集合中的观察者
     * @param observer
     */
    public synchronized void deleteObserver(Observer observer){
        if(observers == null || observers.size() <= 0){
            return ;
        }

        observers.remove(observer);
    }

    /**
     * 通知所有的观察者状态的变化--push模型
     * @param state
     */
    public void notifyObserver(String state){
        for(Observer observer : observers){
            observer.update(state);
        }
    }

    /**
     * 通知观察者目前状态已经发生状态--pull模型
     * 被观察者直接将本身发送给观察者，观察者根据自己需要获取相应的数据
     */
    public void notifyObserver(){
        for(Observer observer : observers){
            observer.update(this);
        }
    }
}
