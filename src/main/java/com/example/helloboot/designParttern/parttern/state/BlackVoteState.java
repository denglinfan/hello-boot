package com.example.helloboot.designParttern.parttern.state;

public class BlackVoteState implements VoteState {

    public void vote(String user, String voteItem, VoteManager voteManager) {
        System.out.println("进入黑名单，将禁止登陆和使用本系统！！！");
    }
}
