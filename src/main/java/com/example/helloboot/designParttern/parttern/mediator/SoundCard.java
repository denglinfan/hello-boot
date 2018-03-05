package com.example.helloboot.designParttern.parttern.mediator;

public class SoundCard extends Colleagues {

    public SoundCard(Mediators mediators) {
        super(mediators);
    }

    public void soundData(String data){
        System.out.println("画外音：" + data);
    }
}
