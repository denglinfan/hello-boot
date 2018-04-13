package com.example.helloboot.concurrency.lockLessSet;

/**
 * 懒惰set的实现
 *  1. add和remove操作和乐观锁的过程基本一致，只是在remove的时候，
 *      先标记节点的逻辑删除状态，再物理删除
 *  2. contains方法可以去掉锁了，注意的事他也是保证快照的正确性
 * @param <T>
 */
public class LazySet<T> implements Set<T> {

    private final NodeWithLockAndmark<T> head;

    public LazySet() {
        head = new NodeWithLockAndmark<T>();
        head.key = Integer.MIN_VALUE;
        NodeWithLockAndmark<T> MAX = new NodeWithLockAndmark<T>();
        MAX.key = Integer.MAX_VALUE;
        head.next = MAX;
    }

    @Override
    public boolean add(T item) {
        NodeWithLockAndmark<T> pred, curr;
        int key = item.hashCode();
        while(true){
            pred = head;
            curr = pred.next;
            while(curr.key < key){
                pred = curr;
                curr = curr.next;
            }
            pred.lock();
            curr.lock();
            try{
                if(validate(pred,curr)){
                    if(curr.key == key){
                        return false;
                    }
                    NodeWithLockAndmark<T> node = new NodeWithLockAndmark<T>(item);
                    node.next = curr;
                    pred.next = node;
                    return true;
                }
            }finally {
                pred.unlock();
                curr.unlock();
            }
        }
    }

    @Override
    public boolean remove(T item) {
        NodeWithLockAndmark<T> pred, curr;
        int key = item.hashCode();
        while(true){
            pred = head;
            curr = pred.next;
            while(curr.key < key){
                pred = curr;
                curr = curr.next;
            }
            pred.lock();
            curr.lock();
            try{
                if(validate(pred,curr)){
                    if(curr.key == key){
                        curr.marked = true;//logical remove Node, use volatile to make sure visibility
                        pred.next = curr.next;
                        curr.next = null;
                        return true;
                    }
                    return false;
                }
            }finally {
                pred.unlock();
                curr.unlock();
            }
        }
    }

    @Override
    public boolean contains(T item) {
        NodeWithLockAndmark<T> curr = head;
        int key = item.hashCode();
        while(curr.key < key){
            curr = curr.next;
        }
        return curr.key == key && !curr.marked;
    }

    private boolean validate(NodeWithLockAndmark<T> pred, NodeWithLockAndmark<T> curr) {
        return !pred.marked && ! curr.marked && pred.next == curr;
    }
}
