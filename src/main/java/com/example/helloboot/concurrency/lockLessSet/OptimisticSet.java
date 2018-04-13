package com.example.helloboot.concurrency.lockLessSet;

/**
 * 基于乐观锁的set实现
 *  1. 先不加锁，直到找到要处理的现场，也就是while(curr.key < key)退出的地方。退出之后有两种情况
 *      curr.key == key 和curr.key > key
 *  2. 找到现场后，要对pred和curr都加锁，加锁顺序也是从前往后的顺序
 *  3.验证现场未被修改，然后进行操作，如果被修改了，就释放锁，再次葱头节点开行轮训操作
 * @param <T>
 */
public class OptimisticSet<T> implements Set<T> {

    private final NodeWithLock<T> head;

    public OptimisticSet() {
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
                    NodeWithLock<T> node = new NodeWithLock<T>(item);
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
        NodeWithLock<T> pred, curr;
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
        NodeWithLock<T> pred, curr;
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
                    return curr.key == key;
                }
            }finally {
                pred.unlock();
                curr.unlock();
            }
        }
    }

    private boolean validate(NodeWithLock<T> pred, NodeWithLock<T> curr) {
        NodeWithLock<T> node = head;
        while(node.key <= pred.key){
            if(node.key == pred.key){
                return pred.next == curr;
            }
            node = node.next;
        }
        return false;
    }
}
