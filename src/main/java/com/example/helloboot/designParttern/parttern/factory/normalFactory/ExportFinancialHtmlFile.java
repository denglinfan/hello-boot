package com.example.helloboot.designParttern.parttern.factory.normalFactory;

public class ExportFinancialHtmlFile implements ExportFile {

    public boolean export(String data) {
        System.out.println("导出财务版html文件！！！");
        return true;
    }
}
