package com.example.helloboot.springBoot.binlogListener.io;

import java.net.Socket;

public interface SocketFactory {

    Socket create(String host, int port) throws Exception;
}
