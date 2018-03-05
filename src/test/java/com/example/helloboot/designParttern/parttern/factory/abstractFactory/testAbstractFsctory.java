package com.example.helloboot.designParttern.parttern.factory.abstractFactory;
import com.example.helloboot.baseUnit.BaseUnit;
import org.junit.Test;

public class testAbstractFsctory extends BaseUnit {

    @Test
    public void test(){
        //创建装机工程师对象
        ComputerEngineer cf = new ComputerEngineer();
        //客户选择并创建需要使用的产品对象
        AbstractFactory af = new IntelFactory();
        //告诉装机工程师自己选择的产品，让装机工程师组装电脑
        cf.makeComputer(af);
    }
}
