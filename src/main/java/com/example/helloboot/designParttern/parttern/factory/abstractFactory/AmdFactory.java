package com.example.helloboot.designParttern.parttern.factory.abstractFactory;

import com.example.helloboot.designParttern.parttern.factory.simpleFactory.AmdCpu;
import com.example.helloboot.designParttern.parttern.factory.simpleFactory.AmdMainboard;
import com.example.helloboot.designParttern.parttern.factory.simpleFactory.Cpu;
import com.example.helloboot.designParttern.parttern.factory.simpleFactory.Mainboard;

public class AmdFactory implements AbstractFactory {

    public Cpu createCpu() {
        return new AmdCpu(938);
    }

    public Mainboard createMainboard() {
        return new AmdMainboard(938);
    }
}
