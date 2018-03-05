package com.example.helloboot.designParttern.Proxy;
import com.example.helloboot.baseUnit.BaseUnit;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class testProxy extends BaseUnit {

    @Test
    public void test(){
        //the truth object that we need proxy
        Subject realSubject = new RealSubject();

        /**
         * We need proxy which real object,the object will push into the DynamicProxy class.
         * then we can use the real object to transfer the method
         */
        InvocationHandler handler = new DynamicProxy(realSubject);

        /**
         * We can use the Proxy-newProxyInstance to create our proxy object,And there have three args:
         *      first:  handler.getClass().getClassLoader(),
         *          We choose the InvocationHandler's classLoader to load our proxy object
         *      second: realSubject.getClass(0.getInterfaces(),
         *          We provide interface which is implements by the real Object to proxy Object,
         *          It mains that we proxy the real Object,Then wo can use the interface's method.
         *      third:  handler
         *          We will make the proxy Object cognate the InvocationHandler
         */
        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(),handler);

        System.out.println(subject.getClass().getName());

        subject.rent();

        subject.hello("world");
    }
}
