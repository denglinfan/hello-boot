package com.example.helloboot.designParttern.parttern.template;
import com.example.helloboot.baseUnit.BaseUnit;
import org.junit.Test;

public class templateTest extends BaseUnit {

    @Test
    public void testClient(){
        Account account = new MoneyMarketAccount();
        System.out.println("货币市场账号的利息数额为：" + account.calculateInterest());
        account = new CDAccount();
        System.out.println("定期账号的利息数额为：" + account.calculateInterest());
    }
}
