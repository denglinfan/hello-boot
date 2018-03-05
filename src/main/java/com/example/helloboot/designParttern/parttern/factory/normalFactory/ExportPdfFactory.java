package com.example.helloboot.designParttern.parttern.factory.normalFactory;

public class ExportPdfFactory implements ExportFactory {

    public ExportFile factory(String type) {
        if("standard".equals(type)){

            return new ExportStandardPdfFile();

        }else if("financial".equals(type)){

            return new ExportFinancialPdfFile();

        }else{
            throw new RuntimeException("没有找到对象");
        }
    }
}
