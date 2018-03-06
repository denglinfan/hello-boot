package com.example.helloboot.concurrency.monitorCondition;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class BlockingArrayTest {

    public static void main(String[] args){
        final BlockingArray<Integer> blockArray = new BlockingArray<Integer>(10);

        /*final BlockArrayWithCondition<Integer> blockArray =
                new BlockArrayWithCondition<Integer>(10);*/
        final AtomicInteger count = new AtomicInteger(0);

        IntStream.range(0,100).forEach(i -> {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        blockArray.put(count.incrementAndGet());
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        });

        IntStream.range(0,100).forEach(i -> {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        blockArray.take();
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        });
    }
}
