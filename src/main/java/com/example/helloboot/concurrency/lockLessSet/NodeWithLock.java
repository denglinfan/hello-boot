package com.example.helloboot.concurrency.lockLessSet;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NodeWithLock<T> {

    T item;
    NodeWithLock<T> next;
    int key;
    Lock lock = new ReentrantLock();

    public NodeWithLock(T item) {
        this.item = item;
        this.key = item.hashCode();
    }

    public NodeWithLock() {
    }

    public void lock(){
        lock.lock();
    }

    public void unlock(){
        lock.unlock();
    }
}
