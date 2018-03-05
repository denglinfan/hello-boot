package com.example.helloboot.designParttern.parttern.template;

public class CDAccount extends Account {

    @Override
    protected double doCalculateInterestRate() {
        return 0.06;
    }

    @Override
    protected String doCalculateAccountType() {
        return "Certificate of Deposite";
    }
}
