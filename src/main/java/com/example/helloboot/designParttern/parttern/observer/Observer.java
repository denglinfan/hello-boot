package com.example.helloboot.designParttern.parttern.observer;

public interface Observer {

    void update(String state);

    void update(Subject subject);
}
