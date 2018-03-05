package com.example.helloboot.designParttern.parttern.strategy;

public class AdvancedMemberStrategy implements MemberStrategy {

    public double calcPrice(double booksPrice) {
        System.out.println("高级会员享受的折扣为20%！！！");
        return booksPrice * 0.8;
    }
}
