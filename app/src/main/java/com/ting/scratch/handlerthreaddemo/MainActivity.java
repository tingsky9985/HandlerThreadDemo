package com.ting.scratch.handlerthreaddemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by ting on 16-8-11.
 */
public class MainActivity extends Activity {

    private static final String TAG =  "MainActivity";

    private MyHandlerThread myHandlerThread;
    private HandlerThread handlerThread;
    private static final int EVENT_MESSAGE = 1;

    private Button bt_operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化组件
        bt_operator = (Button) findViewById(R.id.bt_operator);

        //初始化HandlerThread
        handlerThread = new HandlerThread("process");
        handlerThread.start();
        myHandlerThread = new MyHandlerThread(handlerThread.getLooper());
    }

    /**
     * 点击事件，将耗时操作发送到HandlerThread中处理
     * @param view
     */
    public void consume(View view){
        myHandlerThread.sendEmptyMessage(EVENT_MESSAGE);
    }

    /**
     * 自定义Handler
     */
    class MyHandlerThread extends Handler{
        public MyHandlerThread(Callback callback) {
            super(callback);
        }

        public MyHandlerThread(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接收耗时操作，后台处理
            switch (msg.what){
                case EVENT_MESSAGE:
                    Log.d(TAG,"get message EVENT_MESSAGE");
                    break;
            }
        }
    }
}
