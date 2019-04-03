package com.example.indext.gtaobao;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
public class Main3Activity extends AppCompatActivity{
    private RadioGroup radiogrouop;
    private Fragment index_page,micro_page,information_page,shopping_page,myto_page;
    private  int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //        隐藏标题栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }




        radiogrouop = findViewById(R.id.radio_Group);
        radiogrouop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();//创建一个事务
                switch (checkedId){
                    case R.id.index_tao:
                        if(index_page==null){
                            index_page=new index_page_fragment();
                        }
                        transaction.replace(R.id.right_fragment,index_page);
                        break;
                    case R.id.micro_tao:
                        if(micro_page==null){
                            micro_page=new micro_naughty_fragment();
                        }
                        transaction.replace(R.id.right_fragment,micro_page);
                        break;
                    case R.id.information_tao:
                        if(information_page==null){
                            information_page=new information_page_fragment();
                        }
                        transaction.replace(R.id.right_fragment,information_page);
                        break;
                    case R.id.shopping_tao:
                        if(shopping_page==null){
                            shopping_page=new shopping_page_fragment();
                        }
                        transaction.replace(R.id.right_fragment,shopping_page);
                        break;
                    case R.id.mytao_tao:
                        if(myto_page==null){
                            myto_page=new mytao_page_fragment();
                        }
                        transaction.replace(R.id.right_fragment,myto_page);
                        break;
                }
                transaction.commit();
            }
        });

    }
    }

