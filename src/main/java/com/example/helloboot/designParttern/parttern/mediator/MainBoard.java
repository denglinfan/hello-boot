package com.example.helloboot.designParttern.parttern.mediator;

public class MainBoard implements Mediators {

    //需要知道要交互的同事类——光驱类
    private CDDriver cdDriver = null;
    //需要知道要交互的同事类——CPU类
    private CPU cpu = null;
    //需要知道要交互的同事类——显卡类
    private VideoCard videoCard = null;
    //需要知道要交互的同事类——声卡类
    private SoundCard soundCard = null;

    public void setCdDriver(CDDriver cdDriver) {
        this.cdDriver = cdDriver;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public void setVideoCard(VideoCard videoCard) {
        this.videoCard = videoCard;
    }

    public void setSoundCard(SoundCard soundCard) {
        this.soundCard = soundCard;
    }

    public void changed(Colleagues c) {
        if(c instanceof CDDriver){
            this.opeCDDriverReadData((CDDriver)c);
        }else if(c instanceof CPU){
            this.opeCPU((CPU) c);
        }
    }

    private void opeCDDriverReadData(CDDriver cd){
        String data = cd.getData();

        cpu.executeData(data);
    }

    private void opeCPU(CPU cpu){
        String videoData = cpu.getVideoData();
        String soundData = cpu.getSoundData();

        videoCard.showData(videoData);
        soundCard.soundData(soundData);
    }
}
