package com.example.helloboot.designParttern.parttern.singleton;
import com.example.helloboot.baseUnit.BaseUnit;
import org.junit.Test;

import java.io.*;

public class TestSingleton extends BaseUnit {

    @Test
    public void test(){
        File file = new File("singletonEnum");
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {

            oos = new ObjectOutputStream(new FileOutputStream(file));
            TestEnumSinglonSafe singleton = EnumSingleton.uniqueInstance.singletonOperation();
            oos.writeObject(EnumSingleton.uniqueInstance.singletonOperation());
            oos.close();
            ois = new ObjectInputStream(new FileInputStream(file));
            TestEnumSinglonSafe singleton2 = (TestEnumSinglonSafe) ois.readObject();
            System.out.println(singleton == singleton2);//true

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
