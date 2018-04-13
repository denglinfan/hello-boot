package com.example.helloboot.concurrency.lockLessSet;

/**
 * 细粒度的set链表实现
 *  1. 获取锁时，先获取pred锁，然后再获取curr的锁
 *  2.当pred和curr的指针往后继节点移动时，要先释放pred锁，然后让pred指向curr，然后curr再指向
 *      next节点，然后curr节点上锁，这样就保证了同时前后两把锁都存在
 *
 * 问题：
 *  当前面的节点被锁住时，后面的节点无法操作，必须等待前面的锁释放
 * @param <T>
 */
public class FineList<T> implements Set<T> {

    private final NodeWithLock<T> head;

    public FineList() {
        head = new NodeWithLock<T>();
        head.key = Integer.MIN_VALUE;
        NodeWithLock<T> MAX = new NodeWithLock<T>();
        MAX.key = Integer.MAX_VALUE;
        head.next = MAX;
    }

    @Override
    public boolean add(T item) {
        NodeWithLock<T> pred, curr;
        int key = item.hashCode();
        head.lock();
        pred = head;
        try{
            curr = pred.next;
            curr.lock();
            try{
                while(curr.key < key){
                    pred.unlock();
                    pred = curr;
                    curr = curr.next;
                    curr.lock();
                }
                if(curr.key == key){
                    return false;
                }
                NodeWithLock<T> node = new NodeWithLock<T>(item);
                node.next = curr;
                pred.next = node;
                return true;
            } finally {
                curr.unlock();
            }
        } finally {
            pred.unlock();
        }
    }

    @Override
    public boolean remove(T item) {
        NodeWithLock<T> pred, curr;
        int key = item.hashCode();
        head.lock();
        pred = head;
        try{
            curr = pred.next;
            curr.lock();
            try{
                while(curr.key < key){
                    pred.unlock();
                    pred = curr;
                    curr = curr.next;
                    curr.lock();
                }
                if(curr.key == key){
                    pred.next = curr.next;
                    curr.next = null;
                    return true;
                }
                return false;
            } finally {
                curr.unlock();
            }
        }finally {
            pred.unlock();
        }
    }

    @Override
    public boolean contains(T item) {
        NodeWithLock<T> pred, curr;
        int key = item.hashCode();
        head.lock();
        pred = head;
        try{
            curr = pred.next;
            curr.lock();
            try{
                while(curr.key < key){
                    pred.unlock();
                    pred = curr;
                    curr = curr.next;
                    curr.lock();
                }
                return curr.key == key;
            }finally {
                curr.unlock();
            }
        }finally {
            pred.unlock();
        }
    }
}
