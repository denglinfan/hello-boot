package com.example.helloboot.designParttern.parttern.factory.normalFactory;

public class ExportStandardHtmlFile implements ExportFile {

    public boolean export(String data) {
        System.out.println("导出标准html文件！！！");
        return true;
    }
}
