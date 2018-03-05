package com.example.helloboot.designParttern.parttern.factory.abstractFactory;

import com.example.helloboot.designParttern.parttern.factory.simpleFactory.Cpu;
import com.example.helloboot.designParttern.parttern.factory.simpleFactory.IntelCpu;
import com.example.helloboot.designParttern.parttern.factory.simpleFactory.IntelMainboard;
import com.example.helloboot.designParttern.parttern.factory.simpleFactory.Mainboard;

public class IntelFactory implements AbstractFactory {

    public Cpu createCpu() {
        return new IntelCpu(755);
    }

    public Mainboard createMainboard() {
        return new IntelMainboard(755);
    }
}
