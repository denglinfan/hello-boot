package com.example.helloboot.concurrency.Mutex;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mutex implements Lock, Serializable{

    /**
     * 内部类，自定义同步器
     */
    private static class Sync extends AbstractQueuedSynchronizer{
        /**
         * 是否处于暂用状态
         * @return
         */
        protected boolean isHeldExclusively(){
            return getState() == 1;
        }

        /**
         * 当状态为0的时候，获取锁
         * @param acquires
         * @return
         */
        public boolean tryAcquire(int acquires){
            assert acquires == 1;
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 释放锁，将状态设置为0
         * @param releases
         * @return
         */
        protected boolean tryRelease(int releases){
            assert releases == 1;
            if(getState() == 0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 返回一个Condition，每个Condition都包含一个condition队列
         * @return
         */
        Condition newCondition(){
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
