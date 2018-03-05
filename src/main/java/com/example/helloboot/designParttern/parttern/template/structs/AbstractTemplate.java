package com.example.helloboot.designParttern.parttern.template.structs;

public abstract class AbstractTemplate {

    /**
     * the template method
     */
    public void templateMethod(){
        abstractMethod();
        hookMethod();
        concreteMethod();
    }

    /**
     * the basic method,child-class must realize this method
     */
    protected abstract void abstractMethod();

    /**
     * this is hook method,child-class can change it if necessary
     */
    protected void hookMethod(){
        System.out.println("this is default method,you can change it if you want!!!");
    }

    /**
     * this is basic method,it already realize,child-class can't overwrite it
     */
    private final void concreteMethod(){
        System.out.println("I'm the parent method!!!");
    }
}
