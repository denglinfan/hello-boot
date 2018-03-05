package com.example.helloboot.designParttern.parttern.factory.normalFactory;

public class ExportFinancialPdfFile implements ExportFile {

    public boolean export(String data) {
        System.out.println("导出财务版pdf文件！！！");
        return true;
    }
}
