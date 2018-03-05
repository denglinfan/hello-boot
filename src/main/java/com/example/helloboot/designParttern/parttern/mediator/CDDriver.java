package com.example.helloboot.designParttern.parttern.mediator;

public class CDDriver extends Colleagues {

    private String data = "";

    public CDDriver(Mediators mediators) {
        super(mediators);
    }

    public String getData() {
        return data;
    }

    public void readCD(){
        this.data = "One Piece,海贼王我当定了！！！";
        getMediator().changed(this);
    }
}
