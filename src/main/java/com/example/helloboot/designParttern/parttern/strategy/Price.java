package com.example.helloboot.designParttern.parttern.strategy;

public class Price {

    /**
     * hold a particular strategy object
     */
    private MemberStrategy strategy;

    /**
     * the constrautor function,
     *  push a particular strategy object
     * @param strategy
     */
    public Price(MemberStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * calculate books price
     * @param booksPrice
     * @return
     */
    public double quote(double booksPrice){
        return this.strategy.calcPrice(booksPrice);
    }
}
