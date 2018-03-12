package com.example.helloboot.concurrency.ReadWriteLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平的读写锁，优先让写锁能更快地获得锁，写锁尝试获得锁时，读锁会进入等待，让写锁先获得锁
 */
public class SimpleFairReadWriteLock implements ReadWriteLock {

    private final Lock lock;
    private final Condition existReadCondition;
    private final Condition existWriteCondition;
    private final Lock readLock;
    private final Lock writeLock;
    private volatile boolean write;
    private volatile int readAccquired;
    private volatile int readReleased;

    public SimpleFairReadWriteLock() {
        lock = new ReentrantLock(true);
        existReadCondition = lock.newCondition();
        existWriteCondition = lock.newCondition();
        readLock = new ReadLock();
        writeLock = new WriteLock();
        write = false;
        readAccquired = 0;
        readReleased = 0;
    }

    @Override
    public Lock readLock() {
        return readLock;
    }

    @Override
    public Lock writeLock() {
        return writeLock;
    }

    private class ReadLock implements Lock{
        @Override
        public void lock() {
            lock.lock();
            try{
                while(write){
                    try{
                        existWriteCondition.await();
                    } catch (InterruptedException e){
                        throw new RuntimeException("Interrupted");
                    }
                }
                readAccquired++;
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
                readReleased++;
                if(readReleased == readAccquired){
                    existReadCondition.signalAll();
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

    private class WriteLock implements Lock{
        @Override
        public void lock() {
            lock.lock();
            try{
                while(write){
                    try{
                        existWriteCondition.await();
                    } catch (InterruptedException e){
                        throw new RuntimeException("Interrupted");
                    }
                }
                write = true;
                while(readAccquired != readReleased){
                    try{
                        existReadCondition.await();
                    } catch (InterruptedException e){
                        throw new RuntimeException("Interrupted");
                    }
                }
            }finally {
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
