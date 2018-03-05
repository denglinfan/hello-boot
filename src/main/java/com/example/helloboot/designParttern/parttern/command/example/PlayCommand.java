package com.example.helloboot.designParttern.parttern.command.example;

public class PlayCommand implements Command {

    private AudioPlayer myAudio;

    public PlayCommand(AudioPlayer myAudio) {
        this.myAudio = myAudio;
    }

    /**
     * 执行方法
     */
    public void execute() {
        myAudio.play();
    }
}
