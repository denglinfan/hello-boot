package com.example.helloboot.concurrency.lockLessSet;

/**
 * 该类标识要操作的现场，它返回pred和curr节点的指针
 * findPosition方法从头结点往后寻找位置， 边寻找边物理删除呗标记逻辑删除的节点。
 *      最后返回的时候的Position的curr.key >= 要操作节点的HashCode
 * @param <T>
 */
public class Position<T> {

    NodeWithAtomicMark<T> pred, curr;

    public Position(NodeWithAtomicMark<T> pred, NodeWithAtomicMark<T> curr) {
        this.pred = pred;
        this.curr = curr;
    }

    public Position() {
    }

    public Position<T> findPosition(NodeWithAtomicMark<T> head, int key){
        boolean[] markHolder = new boolean[1];
        NodeWithAtomicMark<T> pred, curr, succ;
        boolean success;
        retry: while(true){
            pred = head;
            curr = pred.next.getReference();
            //清除被逻辑删除的节点，也就是被标记的节点
            while(true){
                succ = curr.next.get(markHolder);
                if(markHolder[0]){
                    success = pred.next.compareAndSet(curr,succ,false,false);
                    if(!success){
                        continue retry;
                    }
                    curr = succ;
                    succ = curr.next.get(markHolder);
                }

                if(curr.key >= key){
                    return new Position<T>(pred, curr);
                }
                pred = curr;
                curr = succ;
            }
        }
    }
}
