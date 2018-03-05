package com.example.helloboot.designParttern.parttern.proxy.structs;

/**
 * This is the proxy object,this class just use the real object method
 */
public class ProxyObject extends AbstractObject {

    /**
     * real object
     */
    public RealObject realObject = new RealObject();

    /**
     * This is the proxy method,
     * it can add some handler before or after you use the real Object method
     */
    @Override
    public void operation() {
        System.out.println("before!!!");

        realObject.operation();

        System.out.println("after!!!");
    }
}
