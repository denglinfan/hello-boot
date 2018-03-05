package com.example.helloboot.designParttern.parttern.factory.normalFactory;

public class ExportStandardPdfFile implements ExportFile {

    public boolean export(String data) {
        System.out.println("导出标准pdf文件！！！");
        return true;
    }
}
