package com.example.helloboot.designParttern.parttern.interpreter;

import com.example.helloboot.baseUnit.BaseUnit;
import org.junit.Test;

public class testInterpret extends BaseUnit {

    @Test
    public void test(){
        Context ctx = new Context();
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Constant c = new Constant(true);
        ctx.assign(x, false);
        ctx.assign(y, true);

        Expression exp = new Or(new And(c,x) , new And(y,new Not(x)));
        System.out.println("x=" + x.interpret(ctx));
        System.out.println("y=" + y.interpret(ctx));
        System.out.println(exp.toString() + "=" + exp.interpret(ctx));
    }
}
