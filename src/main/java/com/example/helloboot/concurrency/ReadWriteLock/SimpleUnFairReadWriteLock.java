package com.example.helloboot.concurrency.ReadWriteLock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * There have two point about the ReadWriteLock
 *  1.It not allowed to have write lock when it have the read or write lock
 *  2.It not allowed have the read and write lock when you got the write lock
 *
 *  It's mean that the write lock is a exclusive lock, it just have one lock at the same time
 *  and read lock is a sharing lock, it can have more than one lock
 */
public class SimpleUnFairReadWriteLock implements ReadWriteLock {

    private final java.util.concurrent.locks.Lock lock;
    private final Condition existReadCondition;
    private final Condition existWriteCondition;
    private final java.util.concurrent.locks.Lock readLock;
    private final java.util.concurrent.locks.Lock writeLock;
    private volatile boolean write;
    private volatile int readCount;

    public SimpleUnFairReadWriteLock() {
        this.lock = new ReentrantLock();
        this.existReadCondition = lock.newCondition();
        this.existWriteCondition = lock.newCondition();
        this.readLock = new ReadLock();
        this.writeLock = new WriteLock();
        this.write = false;
        this.readCount = 0;
    }

    @Override
    public Lock readLock() {
        return readLock;
    }

    @Override
    public Lock writeLock() {
        return writeLock;
    }

    private class ReadLock implements Lock {
        @Override
        public void lock() {
            lock.lock();
            try{
                while(write){
                    try{
                        existWriteCondition.await();
                    } catch(InterruptedException e){
                        throw new RuntimeException("Interrupted");
                    }
                }
                readCount++;
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
                readCount--;
                if(readCount == 0){
                    existReadCondition.signalAll();
                }
            }finally {
                lock.unlock();
            }
        }

        @Override
        public Condition newCondition() {
            return null;
        }
    }

    private class WriteLock implements Lock{
        @Override
        public void lock() {
            lock.lock();
            try{
                while(readCount > 0){
                    try{
                        existReadCondition.await();
                    } catch (InterruptedException e){
                        throw new RuntimeException("Interrupted");
                    }
                }
                while(write){
                    try{
                        existWriteCondition.await();
                    } catch(InterruptedException e){
                        throw new RuntimeException("Interrupted");
                    }
                }
                write = true;
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
                write = false;
                existWriteCondition.signalAll();
            } finally {
                lock.unlock();
            }
        }

        @Override
        public Condition newCondition() {
            return null;
        }
    }
}
