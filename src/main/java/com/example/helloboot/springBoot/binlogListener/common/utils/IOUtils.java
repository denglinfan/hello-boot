package com.example.helloboot.springBoot.binlogListener.common.utils;

import com.example.helloboot.springBoot.binlogListener.io.XInputStream;
import com.example.helloboot.springBoot.binlogListener.io.XOutputStream;

import java.io.IOException;
import java.net.Socket;

public class IOUtils {

    public static void closeQuietly(Socket socket){
        try {
            socket.close();
        } catch (IOException e) {

        }
    }

    public static void closeQuietly(XInputStream is){
        try {
            is.close();
        } catch (IOException e) {

        }
    }

    public static void closeQuietly(XOutputStream os){
        try {
            os.close();
        } catch (IOException e) {

        }
    }
}
