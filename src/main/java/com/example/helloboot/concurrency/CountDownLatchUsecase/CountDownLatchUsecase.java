package com.example.helloboot.concurrency.CountDownLatchUsecase;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchUsecase {

    private int nThreads;

    private CountDownLatch startLatch;

    private CountDownLatch endLatch;

    public CountDownLatchUsecase(int n) {
        this.nThreads = n;
        startLatch = new CountDownLatch(1);
        endLatch = new CountDownLatch(n);
    }

    public void race() throws InterruptedException{
        System.out.println("Thread " + Thread.currentThread().getName() + " is waiting the resource");
        startLatch.await();
        System.out.println("Thread " + Thread.currentThread().getName() + " got the resource");
        endLatch.countDown();
    }

    public void start(){
        startLatch.countDown();
    }

    public void end() throws InterruptedException{
        endLatch.await();
    }

    public static void main(String[] args) throws Exception{
        final CountDownLatchUsecase usecase = new CountDownLatchUsecase(10);
        for(int i = 0;i < 10; i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        usecase.race();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },String.valueOf(i));
            t.start();
        }

        Thread.sleep(3000);
        System.out.println("Now start!!!");
        usecase.start();
        usecase.end();
        System.out.println("All Thread finished");
    }
}
