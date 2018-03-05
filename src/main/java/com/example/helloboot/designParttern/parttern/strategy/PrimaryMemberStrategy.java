package com.example.helloboot.designParttern.parttern.strategy;

public class PrimaryMemberStrategy implements MemberStrategy {

    public double calcPrice(double booksPrice) {
        System.out.println("初级会员不享受折扣价！！！");
        return booksPrice;
    }
}
