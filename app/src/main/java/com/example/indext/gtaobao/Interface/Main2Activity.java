package com.example.indext.gtaobao.Interface;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.indext.gtaobao.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {
    public static DaoSession mDaoSession;
    private TextView txt;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private List<Msg> msgList=new ArrayList<>();
    private EditText input;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    private String key="af99cbcf2dff4a2d8c04e0bd5b95d701";
    private String names;
    private  int page;
    private SwipeRefreshLayout swipefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt=findViewById(R.id.msg_txt);
        Intent intent=getIntent();
        names=intent.getStringExtra("name");
        txt.setText(names);
        setDatabase();
        /***
         * 加载数据库
         */
            initF();
            input=(EditText)findViewById(R.id.input_Text);
            send=(Button)findViewById(R.id.send);
            msgRecyclerView=(RecyclerView)findViewById(R.id.msg_recycler_view);
//            msgList=mDaoSession.getMsgDao().loadAll();
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            msgRecyclerView.setLayoutManager(layoutManager);
            adapter=new MsgAdapter(msgList);
            msgRecyclerView.setAdapter(adapter);

            swipefresh=findViewById(R.id.swipe_fresh);
            swipefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refresh();
                }

                private void refresh() {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    frish();
                                    adapter.notifyDataSetChanged();
                                    swipefresh.setRefreshing(false);
                                }
                            });
                        }
                    }).start();
                }
            });



            send.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String content=input.getText().toString();
                    if(!"".equals(content)){
                        Msg msg=new Msg(null,content,Msg.TYPE_SENT,0,getIntent().getStringExtra("name"),null,null,0);
                        msgList.add(msg);
                        mDaoSession.getMsgDao().insert(msg);
//                  当前有新消息是，刷新RecyclerView中的显示
                        adapter.notifyItemInserted(msgList.size());
                        msgRecyclerView.scrollToPosition(msgList.size());
//                    清空输入框内容
                        input.setText("");

                        String url = "http://www.tuling123.com/openapi/api?key=" + key + "&info=" + content;
                        HttpUtil.sendOkHttpRequest(url, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                showDate(response.body().string());
                            }
                        });
                    }
                }
            });


//        隐藏标题栏
            ActionBar actionBar=getSupportActionBar();
            if(actionBar!=null){
                actionBar.hide();
            }
        }
    public void showDate(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<String> text = Analysis2(string);
                for (int i = 0; i < text.size(); i++) {
                    Msg tuLingMsg = new Msg(null,text.get(i), Msg.TYPE_RECEIVED, getIntent().getIntExtra("img",0),names,null,null,0);
                    msgList.add(tuLingMsg);
                    mDaoSession.getMsgDao().insert(tuLingMsg);
                }
//                String[] text = Analysis(string);
//                for (int i = 0; i < text.length; i++) {
//                    TuLingMsg tuLingMsg = new TuLingMsg(text[i], TuLingMsg.LEFT);
//                    list.add(tuLingMsg);
//                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                adapter.notifyItemInserted(msgList.size() - 1);
                msgRecyclerView.scrollToPosition(msgList.size() - 1);
            }
        });

    }


    public List<String> Analysis2(String string) {

        try {
            JSONObject jsonObject = new JSONObject(string);
            List<String> content = new ArrayList<>();
            content.add(jsonObject.getString("text"));
            if ("200000".equals(jsonObject.getString("code"))) {
                content.add(jsonObject.getString("url"));
            }
            return content;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    /***
     * 初始化数据库
     */
    public void initF(){
        Long c=mDaoSession.getMsgDao().queryBuilder().where(MsgDao.Properties.Name1.eq(names)).count();
        if(c.byteValue()%10==0) {
            page = c.intValue() / 10 - 1;
            msgList.addAll(mDaoSession.getMsgDao().queryBuilder().where(MsgDao.Properties.Name1.eq(names)).offset(page-- *10).limit(10).list());
        }else {
            page = c.intValue() / 10;
            msgList.addAll(mDaoSession.getMsgDao().queryBuilder().where(MsgDao.Properties.Name1.eq(names)).offset(page-- *10).limit(10).list());
        }

    }
    /***
     * 刷新加载数据库数据
     */
    private void frish(){
        if(page>=0) {
            msgList.addAll(0, mDaoSession.getMsgDao().queryBuilder().where(MsgDao.Properties.Name1.eq(names)).offset((page--) * 10).limit(10).build().list());
        }else {
            Toast.makeText(this, "消息加载完毕", Toast.LENGTH_SHORT).show();
        }
    }

    /***
     * 创建数据库
     */
    private void setDatabase(){
        //创建数据库
        mHelper=new DaoMaster.DevOpenHelper(this,"sp-db",null);
        //获取可用数据库
        db=mHelper.getWritableDatabase();
        //获取数据库对象
        mDaoMaster=new DaoMaster(db);
        //获取dao对象管理者
        mDaoSession=mDaoMaster.newSession();
    }
}
