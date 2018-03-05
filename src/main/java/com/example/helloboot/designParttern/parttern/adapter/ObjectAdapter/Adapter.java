package com.example.helloboot.designParttern.parttern.adapter.ObjectAdapter;

/**
 * This is the Obect Adapter,and there just have one different between this and class Adapter
 *      this don't need to extends or implements other class or implements,
 *      it just use the Object to make the mehtod can be used
 */
public class Adapter implements Target {

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    /**
     * Because of the Source class have this method,
     * so the adapter just direct delegation to use the method.
     */
    public void sampleOperation1(){
        this.adaptee.sampleOperation1();
    }

    /**
     * The Source class don't have this method,
     * so it need to add by Adapter class
     */
    public void sampleOperation2(){
        //write your particular code
    }
}
