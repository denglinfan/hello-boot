package com.example.helloboot.designParttern.parttern.decorator;

/**
 * Decorator role
 */
public class Change implements TheGreatestSage {

    private TheGreatestSage sage;

    public Change(TheGreatestSage sage) {
        this.sage = sage;
    }

    public void move() {
        sage.move();
    }
}
