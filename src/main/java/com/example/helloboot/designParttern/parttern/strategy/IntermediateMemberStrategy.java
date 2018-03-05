package com.example.helloboot.designParttern.parttern.strategy;

public class IntermediateMemberStrategy implements MemberStrategy {

    public double calcPrice(double booksPrice) {
        System.out.println("中级会员享受的折扣价为10%！！！");
        return booksPrice * 0.9;
    }
}
