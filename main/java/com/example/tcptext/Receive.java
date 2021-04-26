package com.example.tcptext;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;

public class Receive extends Thread {
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private int port;
    private String ip;
    private String string;
    private MainActivity mainActivity;

    public Receive(int port, String ip) {
        this.port = port;
        this.ip = ip;
    }

    @Override
    public void run() {
            try {
                //sleep(1000);
                Socket socket = new Socket(ip,port);
                inputStream = socket.getInputStream();
                final byte[] buffer = new byte[1024];
                final int len = inputStream.read(buffer);
                System.out.println(new String(buffer,0,len));
                Log.v("AndroidChat","接收成功："+new String(buffer,0,len));
                string = new String(buffer,0,len);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //catch (InterruptedException e) {
                //e.printStackTrace();
            //}

        //}
        /*try {
            Socket socket = new Socket(ip,port);
            socket.shutdownOutput();
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String msg = null;
            msg = bufferedReader.readLine();
            while((msg=bufferedReader.readLine()) != null) {
                inputStreamReader.close();
                Log.v("AndroidChat","服务器说："+msg);
                System.out.println("服务器说："+msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    /*new Runnable(){
                    @Override
                    public void run() {

                    }
                };*/
    }
    public class MainActivity extends AppCompatActivity{
        private TextView mTv_recive;
        private Button mBtn_recive;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mTv_recive = findViewById(R.id.receive);
            mBtn_recive = findViewById(R.id.text);


            mBtn_recive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"123",Toast.LENGTH_SHORT).show();
                                    System.out.println(string);
                                }
                            });
                        }
                    }).start();
                }
            });
        }
    }
}