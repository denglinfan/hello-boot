package com.example.helloboot.designParttern.parttern.factory.simpleFactory;
import com.example.helloboot.baseUnit.BaseUnit;
import org.junit.Test;

public class testSimpleFactory extends BaseUnit {

    @Test
    public void test(){
        ComputerEngineer cf = new ComputerEngineer();
        cf.makeComputer(1,1);
    }
}
