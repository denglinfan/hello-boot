package com.example.helloboot.designParttern.parttern.singleton;

public enum EnumSingleton {

    /**
     * 定义一个枚举的元素，它就代表了Singleton的一个实例。?????????????
     */
    uniqueInstance;

    private TestEnumSinglonSafe testEnumSinglonSafe;

    private EnumSingleton() {
        testEnumSinglonSafe = new TestEnumSinglonSafe();
    }

    /**
     * 单例可以有自己的操作
     */
    public TestEnumSinglonSafe singletonOperation(){
        //功能处理
        return testEnumSinglonSafe;
    }
}
