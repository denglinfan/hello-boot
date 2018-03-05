package com.example.helloboot.designParttern.parttern.observer;
import com.example.helloboot.baseUnit.BaseUnit;
import org.junit.Test;

/**
 * this class just test the Observer/Subject parttern,
 * there have two different way to get the data.
 * 1.the Observer can get All Data no matter it need or not
 * 2.the Subject will push himself to the Observer,And Observer can get some data which he need.
 */
public class ObserverTest extends BaseUnit {

    @Test
    public void testPush(){
        ConcreteSubject subject = new ConcreteSubject();

        Observer observer = new ConcreteObjserver();

        subject.addObserver(observer);

        subject.change("I'm changing now,you can get all data even you don't need it!");
    }

    @Test
    public void testPull(){
        ConcreteSubject subject = new ConcreteSubject();

        Observer observer = new ConcreteObjserver();

        subject.addObserver(observer);

        subject.changePull("I‘m changing now,and you can get some data if you want!");
    }
}
