package com.example.helloboot.designParttern.parttern.dercoator;
import com.example.helloboot.baseUnit.BaseUnit;
import com.example.helloboot.designParttern.parttern.decorator.Bird;
import com.example.helloboot.designParttern.parttern.decorator.Fish;
import com.example.helloboot.designParttern.parttern.decorator.Monkey;
import com.example.helloboot.designParttern.parttern.decorator.TheGreatestSage;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class Decorator extends BaseUnit {

    @Test
    public void test(){
        TheGreatestSage sage = new Monkey();
        // 第一种写法
        TheGreatestSage bird = new Bird(sage);
        TheGreatestSage fish = new Fish(bird);
        // 第二种写法
        //TheGreatestSage fish = new Fish(new Bird(sage));
        fish.move();
    }

    @Test
    public void testIO(){
        try {
            /*DataInputStream dis = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream("test.txt")
                    )
            );*/
            DataInputStream dis = new DataInputStream(new FileInputStream("text.txt"));
            //读取文件内容
            byte[] bs = new byte[dis.available()];
            dis.read(bs);
            String content = new String(bs);
            System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
