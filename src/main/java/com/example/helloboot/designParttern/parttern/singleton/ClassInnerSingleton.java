package com.example.helloboot.designParttern.parttern.singleton;

public class ClassInnerSingleton {

    private ClassInnerSingleton(){}

    /**
     *  Class level internal classes,also the static member's inter-class,
     *  This class don't bonding with the outer-class.And it just be loading
     *  when it can be transfer,thus it achieve the lazy loading.
     */
    private static class SingletonHolder{
        /**
         *  static initialize,it can ensure thread-safe by JVM
         */
        private static ClassInnerSingleton innerSingleton = new ClassInnerSingleton();
    }

    public static ClassInnerSingleton getInstance(){
        return SingletonHolder.innerSingleton;
    }
}
