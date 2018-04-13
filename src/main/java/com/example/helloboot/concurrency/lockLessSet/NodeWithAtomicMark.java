package com.example.helloboot.concurrency.lockLessSet;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * 1. next的标记状态表示的是当前节点是否被逻辑删除，通过CAS判断这个状态可以知道当前节点是否被逻辑删除了
 * 2. 通过CAS比较next的Rederence就可以知道当前节点和next节点的物理关系是否被修改了，
 *      也是就是可以知道下一个节点是否被物理删除了
 * 3. 由于next标记的状态是当前节点的状态，所以当前节点是无法知道下一个节点是否被逻辑删除了的。
 *      这个点很重要，因为无锁的添加可能会出现添加的节点的后续节点是一个已经被逻辑删除，
 *      但是还没有物理删除的节点
 * @param <T>
 */
public class NodeWithAtomicMark<T> {

    T item;
    AtomicMarkableReference<NodeWithAtomicMark<T>> next =
            new AtomicMarkableReference<NodeWithAtomicMark<T>>(null,false);
    int key;

    public NodeWithAtomicMark(T item) {
        this.item = item;
        this.key = item.hashCode();
    }

    public NodeWithAtomicMark() {
    }
}
