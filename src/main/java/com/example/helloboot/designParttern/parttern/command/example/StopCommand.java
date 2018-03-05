package com.example.helloboot.designParttern.parttern.command.example;

public class StopCommand implements Command {

    private AudioPlayer myAudio;

    public StopCommand(AudioPlayer myAudio) {
        this.myAudio = myAudio;
    }

    public void execute() {
        myAudio.stop();
    }
}
