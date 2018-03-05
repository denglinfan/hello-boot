package com.example.helloboot.designParttern.parttern.state.structs;

public class Context {

    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public void request(String sampleParamter){
        state.handle(sampleParamter);
    }
}
