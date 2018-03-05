package com.example.helloboot.designParttern.parttern.state;

public class NormalVoteState implements VoteState {

    public void vote(String user, String voteItem, VoteManager voteManager) {
        voteManager.getMapVote().put(user,voteItem);
        System.out.println("恭喜投票成功！！！");
    }
}
