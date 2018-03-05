package com.example.helloboot.designParttern.parttern.proxy;
import com.example.helloboot.baseUnit.BaseUnit;
import com.example.helloboot.designParttern.parttern.proxy.structs.AbstractObject;
import com.example.helloboot.designParttern.parttern.proxy.structs.ProxyObject;
import org.junit.Test;

public class TestProxy extends BaseUnit {

    @Test
    public void test(){
        AbstractObject obj = new ProxyObject();
        obj.operation();
    }
}
