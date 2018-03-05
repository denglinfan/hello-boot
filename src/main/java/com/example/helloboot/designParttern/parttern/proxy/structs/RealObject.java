package com.example.helloboot.designParttern.parttern.proxy.structs;

/**
 * the realObject,which need to be proxy
 */
public class RealObject extends AbstractObject {

    @Override
    public void operation() {
        System.out.println("some handler!!!");
    }
}
