package com.example.indext.gtaobao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class welcom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getSupportActionBar().hide();//隐藏标题栏

        Thread myThread=new Thread(){
            //创建子线程
            @Override
            public void run(){
                try {
                    sleep(2000);
                    Intent init=new Intent(getApplicationContext(),Main3Activity.class);//启动MainActivity页面
                    startActivity(init);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
