package com.example.helloboot.concurrency.lockLessSet;

/**
 * 无锁set的实现：
 *      1.无锁添加的时候，先用Position来寻找现场，隐含了一点是寻找的时候同时物理删除了节点
 *      2.判断是否存在的时候也是判断的快照状态，只保证弱一致性
 *      3.如果找到要插入的现场，新建一个节点，并把next指向curr。这里需要理解curr可能被标记了逻辑删除
 *          后面pred的next字段的CAS操作会保证curr没有被物理删除，但是无法知道curr是否被逻辑删除了，
 *          所以新加入的节点的next可能指向了一个被逻辑删除的节点，但是不影响逻辑，
 *          因为下一个Position。findPosition操作会正确的删除被标记逻辑删除的节点。contains操作也会判断
 *          节点是否被逻辑删除
 *      4.pred.next通过AtomicMarkableReference的CAS操作保证了curr节点没有被物理删除
 *          如果CAS成功，说明添加成功
 *      5.删除也一样，先寻找现场，同时物理删除被标记的节点
 *      6.找到现场后，先CAS修改当前节点的状态为被标记逻辑删除
 *      7.尝试一次物理删除，如果这里物理删除成功了，那么如果正好有一个add在同样的现场操作，那么add的
 *          pred的CAS操作会失败。如果这里物理删除失败了，那就把逻辑删除的节点保留在链表中，
 *          等下一次findPosition操作来真正的物理删除
 *      8.contains操作从头结点开始遍历，判断节点内容，并且没有被标记物理删除
 *      9.所有的CAS操作都配合了轮询，这样保证最终的CAS操作的成功
 * @param <T>
 */
public class LockFreeSet<T> implements Set<T> {

    private final NodeWithAtomicMark<T> head;

    public LockFreeSet() {
        head = new NodeWithAtomicMark<T>();
        head.key = Integer.MIN_VALUE;
        NodeWithAtomicMark<T> MAX = new NodeWithAtomicMark<T>();
        MAX.key = Integer.MAX_VALUE;
        head.next.set(MAX,false);
    }

    @Override
    public boolean add(T item) {
        NodeWithAtomicMark<T> pred, curr;
        int key = item.hashCode();
        Position p = new Position();
        while(true){
            Position position = p.findPosition(head,key);
            pred = position.pred;
            curr = position.curr;
            //如果已经存在，只能保证弱一致性，这里只是一个当时状态的快照，
            // 有可能相等的节点在这之后被其他线程已经被删除了，但是这里不能看到
            if(curr.key == key){
                return false;
            }
            NodeWithAtomicMark<T> node = new NodeWithAtomicMark<T>(item);
            node.next.set(curr,false);
            /**
             * 二元状态保证：
             *  1.pred是未被标记的
             *  2.curr未被物理删除，还是让pred的后续节点，这时候即使curr已经被逻辑删除了，
             *      也不影响添加成功。curr会在下一次find被物理删除
             * 二元状态无法知道curr是否被逻辑删除，因为mark表示的是自己节点的状态。
             *  但是curr是否被逻辑删除不影响添加成功，只要不被物理删除就行
             */
            if(pred.next.compareAndSet(curr,node,false,false)){
                return true;
            }
        }
    }

    @Override
    public boolean remove(T item) {
        NodeWithAtomicMark<T> pred, curr;
        int key = item.hashCode();
        Position p = new Position();
        boolean success = false;
        while(true){
            Position position = p.findPosition(head, key);
            pred = position.pred;
            curr = position.curr;
            /**
             * 如果已经存在，只能保证弱一致性，这里只是一个当时状态的快照，有可能相等的节点在这之后被其他线程已经
             * 被删除了，但是这里不能看到
             */
            if(curr.key == key){
                NodeWithAtomicMark<T> succ = curr.next.getReference();
                success = curr.next.compareAndSet(succ,curr,false,true);
                if(!success){
                    continue;
                }
                pred.next.compareAndSet(curr,succ,false,false);
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public boolean contains(T item) {
        NodeWithAtomicMark<T> curr = head;
        int key = item.hashCode();
        boolean[] markHolder = new boolean[1];
        while(curr.key < key){
            curr = curr.next.getReference();
            //检查是否被删除
            curr.next.get(markHolder);
        }
        return curr.key == key && !markHolder[0];
    }
}
