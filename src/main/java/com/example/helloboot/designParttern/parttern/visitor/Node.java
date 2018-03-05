package com.example.helloboot.designParttern.parttern.visitor;

public abstract class Node {

    /**
     * accept the concrete handle
     * @param visitor
     */
    public abstract void accept(Visitor visitor);
}
