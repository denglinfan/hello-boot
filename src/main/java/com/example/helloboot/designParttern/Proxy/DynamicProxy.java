package com.example.helloboot.designParttern.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    /**
     * this is the truth object which we need proxy
     */
    private Object subject;

    /**
     * the constructor method,we need to push a value to the truth object
     * @param subject
     */
    public DynamicProxy(Object subject) {
        this.subject = subject;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //before we the proxy object,we can add some method if we need.
        System.out.println("before rent house!!!");

        System.out.println("Method: " + method);

        /**
         * When proxy object use the truth object's method,
         * It can auto turn to the handler's invoke
         * which is the Association by the proxy object
         */
        method.invoke(subject,args);

        //after we transfer truth object,wo can add some method if we need.
        System.out.println("after rent house!!!");

        return null;
    }
}
