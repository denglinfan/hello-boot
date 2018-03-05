package com.example.helloboot.designParttern.parttern.visitor;
import com.example.helloboot.baseUnit.BaseUnit;
import org.junit.Test;

public class testVisitor extends BaseUnit {

    @Test
    public void test(){
        //创建一个结构对象
        ObjectStructure os = new ObjectStructure();
        //给结构增加一个节点
        os.add(new NodeA());
        //给结构增加一个节点
        os.add(new NodeB());
        //创建一个访问者
        Visitor visitor = new VisitorA();
        os.action(visitor);
    }
}
