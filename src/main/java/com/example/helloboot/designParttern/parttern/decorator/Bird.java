package com.example.helloboot.designParttern.parttern.decorator;

public class Bird extends Change {

    public Bird(TheGreatestSage sage) {
        super(sage);
    }

    @Override
    public void move() {
        System.out.println("Bird Move!!!");
    }
}
