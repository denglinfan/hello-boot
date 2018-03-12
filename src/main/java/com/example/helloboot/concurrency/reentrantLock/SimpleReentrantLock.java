package com.example.helloboot.concurrency.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleReentrantLock implements Lock {

    //指向已经获得锁的线程
    private volatile Thread exclusiveOwnerThread;
    //记录获取了同一个锁的次数
    private volatile int holdCount;
    private final Lock lock;
    //是否是自己获取了锁
    private final Condition isCountZero;

    public SimpleReentrantLock() {
        lock = new ReentrantLock();
        isCountZero = lock.newCondition();
        holdCount = 0;
    }

    @Override
    public void lock() {
        lock.lock();
        try{
            //当前线程的引用
            Thread currentThread = Thread.currentThread();
            //如果获得锁的线程是自己，那么计数器加1，直接返回
            if(exclusiveOwnerThread == currentThread){
                holdCount++;
                return;
            }

            while (holdCount != 0) {
                try{
                    isCountZero.await();
                } catch (InterruptedException e){
                    throw new RuntimeException("Interrupted");
                }
            }
            //将exclusiveOwnerThread设置为自己
            exclusiveOwnerThread = currentThread;
            holdCount++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        lock.lock();
        try{
            holdCount--;
            if(holdCount == 0){
                isCountZero.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
