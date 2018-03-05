package com.example.helloboot.designParttern.parttern.visitor;

public class VisitorB implements Visitor {

    public void visit(NodeA node) {
        System.out.println(node.operationA());
    }

    public void visit(NodeB node) {
        System.out.println(node.operationB());
    }
}
