package com.example.helloboot.designParttern.parttern.state;
import com.example.helloboot.baseUnit.BaseUnit;
import com.example.helloboot.designParttern.parttern.state.structs.ConcreteStateB;
import com.example.helloboot.designParttern.parttern.state.structs.Context;
import com.example.helloboot.designParttern.parttern.state.structs.State;
import org.junit.Test;

public class testState extends BaseUnit {

    @Test
    public void test(){
        //创建状态
        State state = new ConcreteStateB();
        //创建环境
        Context context = new Context();
        //将状态设置到环境中
        context.setState(state);
        //请求
        context.request("test");
    }

    @Test
    public void testVote(){
        VoteManager vm = new VoteManager();
        for(int i=0;i<9;i++){
            vm.vote("u1","A");
        }
    }
}
