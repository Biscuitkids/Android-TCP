package com.example.tcptext;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
     private Button mBtn_send;
     private Button mBtn_connect;
     private EditText mEt_send;
     private static TextView mTv_recv;
     private EditText mEt_ip;
     private EditText mEt_port;
     private String ip;
     private int port;
     private String msg;
     private ConnectThread ct;
     private Send send;
     private Receive receive;
     private Button mBtn_recive;
     static Receive re = new Receive();
     static boolean flag = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv_recv = findViewById(R.id.receive);
        mBtn_connect=findViewById(R.id.connect);
        mBtn_send=findViewById(R.id.btn_1);


        final EditText mEt_ip=this.findViewById(R.id.ip);
        final EditText mEt_port=this.findViewById(R.id.port);
        final EditText mEt_send=this.findViewById(R.id.send);



        mBtn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ip = mEt_ip.getText().toString();//获取输入的ip
                port = Integer.parseInt(mEt_port.getText().toString());//获取输入的端口号
                ct = new ConnectThread(ip,port,re);//创建一个线程来处理消息的收发
                ct.start();//启动连接接收线程
            }
        });

        mBtn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    msg = mEt_send.getText().toString();//将send文本框的信息放在msg中
                    send = new Send(msg,port,ip);//将msg，port，ip传输到send线程
                    send.start();//启动send线程
            }
        });

        }
        //下面是线程间的数据传递
    static class Receive{
        private String string;//声明一个数组string
        public String getString(){
            return string;//可以给string设置初值
        }
        public void setString(String s){
            this.string = s;//用来接收其他线程的数据
        }
    }
    public static void callback(){
        System.out.println("连接线程执行结束");
        flag = false;
        mTv_recv.setText(re.getString());//将接收线程过来显示在接收区
    }
    }
