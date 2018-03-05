package com.example.helloboot.designParttern.parttern.mediator;

public class CPU extends Colleagues {

    private String videoData = "";


    private String soundData = "";

    public CPU(Mediators mediators) {
        super(mediators);
    }

    public String getVideoData() {
        return videoData;
    }

    public String getSoundData() {
        return soundData;
    }

    public void executeData(String data){
        String[] array = data.split(",");
        this.videoData = array[0];
        this.soundData = array[1];

        getMediator().changed(this);
    }
}
