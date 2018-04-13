package com.example.helloboot.concurrency.exchanger;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class SimpleExchanger {

    static class Slot{
        final Object offer;
        final Thread waiter;
        volatile Object other;

        public Slot(Object offer, Thread waiter) {
            this.offer = offer;
            this.waiter = waiter;
        }
    }

    private volatile AtomicReference<Slot> refer = new AtomicReference<>();

    public Object exchange(Object offer){
        Slot me = new Slot(offer, Thread.currentThread());
        Slot you;
        for(;;){
            if((you = refer.get()) == null){
                if(refer.compareAndSet(null,me)){
                    LockSupport.unpark(you.waiter);
                    return you.offer;
                }
            }
        }
    }
 }
