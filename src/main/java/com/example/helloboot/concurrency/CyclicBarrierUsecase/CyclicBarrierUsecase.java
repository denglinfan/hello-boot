package com.example.helloboot.concurrency.CyclicBarrierUsecase;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierUsecase {

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
        @Override
        public void run() {
            System.out.println("CallBack is running");
        }
    });

    public void race() throws Exception{
        System.out.println("Thread " + Thread.currentThread().getName() + " is waiting the resource");
        cyclicBarrier.await();
        System.out.println("Thread " + Thread.currentThread().getName() + " got the resource");
    }

    public static void main(String[] args){
        final CyclicBarrierUsecase usecase = new CyclicBarrierUsecase();

        for(int i = 0; i < 12; i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        usecase.race();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },String.valueOf(i));
            t.start();
        }
    }
}
