package com.example.helloboot.designParttern.parttern.singleton;

/**
 * The LazySingleton:
 *      This is a unsafe thread class,
 *      so we need use synchronized to keep this method is a safe method
 *      when it run in mulit env.
 *
 *      this instance will be initialize when someone want to use it.
 *      Otherwise,it cannot be initialize.
 */
public class LazySingleton {

    private static LazySingleton singleton = null;

    /**
     * The default private constructor
     */
    private LazySingleton() {
    }

    public static synchronized LazySingleton getInstance(){
        if(singleton == null){
            singleton = new LazySingleton();
        }
        return singleton;
    }
}
