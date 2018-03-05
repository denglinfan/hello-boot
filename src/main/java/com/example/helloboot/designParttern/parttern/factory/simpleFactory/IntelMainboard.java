package com.example.helloboot.designParttern.parttern.factory.simpleFactory;

public class IntelMainboard implements Mainboard {

    private int cpuHoles = 0;

    public IntelMainboard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }

    public void installCPU() {
        System.out.println("Intel的主板的cpu插槽孔数是：" + cpuHoles);
    }
}
