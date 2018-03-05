package com.example.helloboot.designParttern.parttern.singleton;

/**
 * note:because this class use volatile.So you must use the jdk version is 1.5 or higher
 */
public class DoubleLockLazySingleton {

    private volatile static DoubleLockLazySingleton instance = null;

    private DoubleLockLazySingleton() {
    }

    public static DoubleLockLazySingleton getInstance(){
        //whether this instance is exist or not,if not,come into this synchronized block
        if(instance == null){
            //it can keep this is a safe space when create the instance
            synchronized (DoubleLockLazySingleton.class){
                //check again,we need to create the real instance when check it not exist
                if(instance == null){
                    instance = new DoubleLockLazySingleton();
                }
            }
        }

        return instance;
    }
}
