package com.example.helloboot.designParttern.parttern.mediator;

public class VideoCard extends Colleagues {

    public VideoCard(Mediators mediators) {
        super(mediators);
    }

    public void showData(String data){
        System.out.println("您正在观看的是：" + data);
    }
}
