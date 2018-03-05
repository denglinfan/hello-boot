package com.example.helloboot.designParttern.parttern.singleton;

/**
 * The eagerSingleton:
 *      When this class is load by jvm,the static variable will be initialize.
 *      And the constructor is define a private method,so it cannot be use in other class or object
 *      When some method want to use the instance ,it can transfer the getInstance method to get this variable
 */
public class EagerSingleton {

    private static EagerSingleton singleton = new EagerSingleton();

    /**
     * The default private constructor
     */
    private EagerSingleton() {
    }

    public static EagerSingleton getInstance(){
        return singleton;
    }
}
