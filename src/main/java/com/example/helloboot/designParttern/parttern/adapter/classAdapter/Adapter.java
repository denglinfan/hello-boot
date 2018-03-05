package com.example.helloboot.designParttern.parttern.adapter.classAdapter;

/**
 * this class is the core about this parttern
 */
public class Adapter extends Adaptee implements Target {

    /**
     * because of the Source class don't have this method,
     * we need to use adapter to add this method
     */
    public void sampleOperation2() {
        //write the particular code
    }
}
