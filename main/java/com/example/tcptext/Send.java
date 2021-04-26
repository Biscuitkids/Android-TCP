package com.example.tcptext;

import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;

public class Send extends Thread {
    private String send;
    private OutputStream outputStream;
    private  InputStream inputStream;
    private String ip;
    private int port;


    public Send(String msg,int port,String ip) {
        this.send = msg;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(ip,port);
            send="客户端发来："+send;//以#来区分是一条消息
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            outputStream.write((send+"\n").getBytes("utf-8"));
            outputStream.flush();
            Log.v("AndroidChat","发送成功："+send);
        }
        catch (Exception e){
            Log.v("AndroidChat","发送失败："+send+"error"+e.getMessage());
            e.printStackTrace();
        }
    }
}
