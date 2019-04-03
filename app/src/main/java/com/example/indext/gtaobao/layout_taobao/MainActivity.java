package com.example.indext.gtaobao.layout_taobao;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.indext.gtaobao.R;
import com.example.indext.gtaobao.layout_taobao.Gtaobao;
import com.example.indext.gtaobao.layout_taobao.GtaobaoAdapter;
import com.example.indext.gtaobao.layout_taobao.gtAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Gtaobao> gtaobaoList = new ArrayList<Gtaobao>();
    private boolean i=true;
    private  RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        /**
         * @param 背景色为白色，字体为黑色
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        Button btn1=(Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"您已退出",Toast.LENGTH_SHORT).show();
                finish();

            }
        });
        final  ImageView img_t1=(ImageView)findViewById(R.id.img_t1);
        final  ImageView img_t2=(ImageView)findViewById(R.id.img_t2);

        img_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setAdapter(new gtAdapter(gtaobaoList));
                img_t2.setVisibility(View.GONE);
                img_t1.setVisibility(View.VISIBLE);

            }
        });
        img_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
                recyclerView.setAdapter(new GtaobaoAdapter(gtaobaoList));
                    img_t1.setVisibility(View.GONE);
                    img_t2.setVisibility(View.VISIBLE);
            }
        });







        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        initF();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        下滑线
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GtaobaoAdapter adapter = new GtaobaoAdapter(gtaobaoList);
        recyclerView.setAdapter(adapter);
    }

    private void initF() {
        for (int i = 0; i < 5; i++) {
            Gtaobao huahua = new Gtaobao("[月销量超千笔] [简约风格]花花公子男士纯棉保暖裤宽松单间2条装","￥89元","5.0万人付款","泰州","进店 >",R.drawable.b);
            gtaobaoList.add(huahua);
            Gtaobao hua = new Gtaobao("【何万阳】","￥79元","850人付款","苏州", "20170232",R.drawable.a);
            gtaobaoList.add(hua);
            Gtaobao hong = new Gtaobao("[月销量超千笔] [简约风格]男士纯","￥69元","1500人付款","上海","进店 >", R.drawable.c);
            gtaobaoList.add(hong);
        }
    }
}
