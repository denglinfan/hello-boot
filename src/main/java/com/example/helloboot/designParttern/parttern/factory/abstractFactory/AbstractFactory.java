package com.example.helloboot.designParttern.parttern.factory.abstractFactory;

import com.example.helloboot.designParttern.parttern.factory.simpleFactory.Cpu;
import com.example.helloboot.designParttern.parttern.factory.simpleFactory.Mainboard;

public interface AbstractFactory {

    public Cpu createCpu();

    public Mainboard createMainboard();
}
