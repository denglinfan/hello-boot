package com.example.helloboot.concurrency.lockLessSet;

public interface Set<T> {

    public boolean add(T item);

    public boolean remove(T item);

    public boolean contains(T item);
}
