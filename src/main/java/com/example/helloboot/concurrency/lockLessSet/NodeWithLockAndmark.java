package com.example.helloboot.concurrency.lockLessSet;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NodeWithLockAndmark<T> {

    T item;
    NodeWithLockAndmark<T> next;
    int key;
    Lock lock = new ReentrantLock();
    volatile boolean marked;

    public NodeWithLockAndmark(T item) {
        this.item = item;
        this.key = item.hashCode();
    }

    public NodeWithLockAndmark() {
    }

    public void lock(){
        lock.lock();
    }

    public void unlock(){
        lock.unlock();
    }
}
