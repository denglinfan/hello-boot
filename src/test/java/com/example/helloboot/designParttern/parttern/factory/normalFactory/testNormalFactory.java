package com.example.helloboot.designParttern.parttern.factory.normalFactory;
import com.example.helloboot.baseUnit.BaseUnit;
import org.junit.Test;

public class testNormalFactory extends BaseUnit {

    @Test
    public void test(){
        String data = "";
        ExportFactory exportFactory = new ExportHtmlFactory();
        ExportFile ef = exportFactory.factory("financial");
        ef.export(data);
    }
}
