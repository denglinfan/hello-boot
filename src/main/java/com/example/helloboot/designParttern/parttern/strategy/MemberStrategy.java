package com.example.helloboot.designParttern.parttern.strategy;

public interface MemberStrategy {

    /**
     * calculate price
     * @param booksPrice the books original cost
     * @return
     */
    double calcPrice(double booksPrice);
}
