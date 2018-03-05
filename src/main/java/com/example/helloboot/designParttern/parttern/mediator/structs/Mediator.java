package com.example.helloboot.designParttern.parttern.mediator.structs;

public interface Mediator {

    /**
     * 同事对象在自身改变的时候来通知调停者方法
     * 让调停者去负责相应的与其他同事对象的交互
     * @param c
     */
    public void changed(Colleague c);
}
