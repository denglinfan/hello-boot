package com.example.helloboot.designParttern.parttern.strategy.structs;

/**
 * this is the env charactor class.
 * It have a strategy(it's a abstract strategy class) object
 */
public class Context {

    private Strategy strategy;

    /**
     * the constructor method,push a particular object
     * @param strategy the particular object
     */
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * the strategy method
     */
    public void contextInterface(){
        strategy.strategyInterface();
    }
}
