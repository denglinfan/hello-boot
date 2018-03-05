package com.example.helloboot.designParttern.parttern.visitor;

public class NodeA extends Node {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationA(){
        return "nodeA";
    }
}
