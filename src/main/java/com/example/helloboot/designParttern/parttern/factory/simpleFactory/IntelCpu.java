package com.example.helloboot.designParttern.parttern.factory.simpleFactory;

public class IntelCpu implements Cpu {

    private int pins = 0;

    public IntelCpu(int pins) {
        this.pins = pins;
    }

    public void calculate() {
        System.out.println("Intel CPU的针脚数：" + pins);
    }
}
