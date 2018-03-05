package com.example.helloboot.designParttern.parttern.command;

/**
 * 请求者角色类
 */
public class Invoker {

    private Command command = null;

    /**
     * 构造方法
     * @param command
     */
    public Invoker(Command command) {
        this.command = command;
    }

    /**
     * 行动方法
     */
    public void action(){
        command.execute();
    }
}
