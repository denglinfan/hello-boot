package com.example.helloboot.designParttern.parttern.factory.simpleFactory;

public class AmdCpu implements Cpu {

    private int pins = 0;

    public AmdCpu(int pins) {
        this.pins = pins;
    }

    public void calculate() {
        System.out.println("AMD CPU的针脚数：" + pins);
    }
}
