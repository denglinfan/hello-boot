package com.example.helloboot.designParttern.parttern.factory.simpleFactory;

public class AmdMainboard implements Mainboard {

    private int cpuHoles = 0;

    public AmdMainboard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }

    public void installCPU() {
        System.out.println("AMD主板的cpu插槽孔数是：" + cpuHoles);
    }
}
