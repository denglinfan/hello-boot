package com.example.helloboot.designParttern.parttern.command;

public class ConcreteCommand implements Command {

    /**
     * 持有相应的接受者对象
     */
    private Receiver receiver = null;

    /**
     * 构造方法
     * @param receiver
     */
    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        //通常会转调用接受者对象的相应方法，让接受者来真正执行功能
        receiver.action();
    }
}
