package com.example.helloboot.designParttern.parttern.adapter.classAdapter;

/**
 * this role is the purpose interface
 */
public interface Target {

    /**
     * the Source class Adaptee also have this method
     */
    public void sampleOperation1();

    /**
     * the Source class Adaptee don't have this method
     */
    public void sampleOperation2();
}
