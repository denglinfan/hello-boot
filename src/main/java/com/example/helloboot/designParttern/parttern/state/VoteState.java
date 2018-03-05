package com.example.helloboot.designParttern.parttern.state;

public interface VoteState {

    /**
     * handle the behavior depend on different state
     * @param user the vote user
     * @param voteItem vote item
     * @param voteManager vote context,transfer the function handle on each state
     */
    public void vote(String user, String voteItem, VoteManager voteManager);
}
