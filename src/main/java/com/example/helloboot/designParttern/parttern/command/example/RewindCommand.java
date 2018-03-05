package com.example.helloboot.designParttern.parttern.command.example;

public class RewindCommand implements Command {

    private AudioPlayer myAudio;

    public RewindCommand(AudioPlayer myAudio) {
        this.myAudio = myAudio;
    }

    public void execute() {
        myAudio.rewind();
    }
}
