package com.example.helloboot.concurrency.lockLessSet;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 粗粒度的链表set实现
 *  1.链表维护了一个头结点，头结点始终指向最小的hashCode，头结点初始的next指针指向最大的hashCode，
 *      表示尾节点，整个链表是按照HashCode从小往大排列
 *  2.链表维护了一个整体的锁，add,remove,contains都加锁，保证线程的安全，简单粗暴，但是效率极低
 * @param <T>
 */
public class CoarSet<T> implements Set<T> {

    private final Node<T> head;
    private Lock lock = new ReentrantLock();

    public CoarSet() {
        head = new Node<T>();
        head.key = Integer.MIN_VALUE;
        Node<T> MAX = new Node<T>();
        MAX.key = Integer.MAX_VALUE;
        head.next = MAX;
    }

    @Override
    public boolean add(T item) {
        Node<T> pred,curr;
        int key = item.hashCode();
        lock.lock();
        try{
            pred = head;
            curr = head.next;
            while(curr.key < key){
                pred = curr;
                curr = curr.next;
            }
            if(curr.key == key){
                return false;
            }

            Node<T> node = new Node<T>(item);
            node.next = curr;
            pred.next = node;
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean remove(T item) {
        Node<T> pred,curr;
        int key = item.hashCode();
        lock.lock();
        try{
            pred = head;
            curr = head.next;
            while(curr.key < key){
                pred = curr;
                curr = curr.next;
            }
            if(curr.key == key){
                pred.next = curr.next;
                curr.next = null;
                return true;
            }

            return false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean contains(T item) {
        Node<T> pred,curr;
        int key = item.hashCode();
        lock.lock();
        try{
            pred = head;
            curr = head.next;
            while(curr.key < key){
                pred = curr;
                curr = curr.next;
            }
            return curr.key == key;
        } finally {
            lock.unlock();
        }
    }
}
