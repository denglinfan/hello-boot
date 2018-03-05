package com.example.helloboot.designParttern.parttern.state;

public class SpiteVoteState implements VoteState {

    public void vote(String user, String voteItem, VoteManager voteManager) {
        String str = voteManager.getMapVote().get(user);
        if(str != null){
            voteManager.getMapVote().remove(user);
        }

        System.out.println("您有恶意刷频的行为，取消投票资格！！！");
    }
}
