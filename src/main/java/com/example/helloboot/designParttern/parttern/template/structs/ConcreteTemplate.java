package com.example.helloboot.designParttern.parttern.template.structs;

/**
 * the child-class realize
 */
public class ConcreteTemplate extends AbstractTemplate {

    /**
     * this method you must realize it
     */
    @Override
    protected void abstractMethod() {

    }

    /**
     * you can overwrite it if you want.
     *  Or just use the default realize
     */
    @Override
    protected void hookMethod() {
        super.hookMethod();
    }
}
