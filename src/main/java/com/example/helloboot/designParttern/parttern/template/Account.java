package com.example.helloboot.designParttern.parttern.template;

/**
 * the abstract class define the basic method and the template
 */
public abstract class Account {

    /**
     * the template method,calculate the interest
     * @return
     */
    public final double calculateInterest(){
        double  interestRate = doCalculateInterestRate();
        String accountType = doCalculateAccountType();
        double amount = calculateAmount(accountType);
        return amount * interestRate;
    }

    protected abstract double doCalculateInterestRate();

    protected abstract String doCalculateAccountType();

    /**
     * this is the default realize.
     * child-class cannot overwrite it.
     * @param accountType
     * @return
     */
    private final double calculateAmount(String accountType){
        return 7243.00;
    }


}
