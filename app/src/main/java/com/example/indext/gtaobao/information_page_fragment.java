package com.example.indext.gtaobao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.indext.gtaobao.Interface.Msg;

import java.util.ArrayList;
import java.util.List;

public class information_page_fragment extends Fragment {
    private List<Msg> Interlist = new ArrayList<Msg>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initF();

        RecyclerView recyclerView = getActivity().findViewById(R.id.information_view);

//        下滑线
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        LinearLayoutManager layoutManager = new
                LinearLayoutManager(getActivity());recyclerView.setLayoutManager(layoutManager);
        InAdapter adapter = new InAdapter(Interlist);
        recyclerView.setAdapter(adapter);
    }
    private void initF() {
        for (int i = 0; i < 5; i++) {
            Msg yin = new Msg(null,null,0,0,"图灵机器人","18/10/08","",R.drawable.tulin);
            Interlist.add(yin);
            Msg bl = new Msg(null,null,0,0,"百丽专卖店","18/10/08","您好",R.drawable.i_b);
            Interlist.add(bl);
            Msg tr= new Msg(null,null,0,0,"唐人乐专卖店","18/10/08","好的",R.drawable.i_c);
            Interlist.add(tr);
            Msg gy = new Msg(null,null,0,0,"谷养康·纯粮酒","18/10/09","好的呢",R.drawable.i_d);
            Interlist.add(gy);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_information_page, container, false);
        inintRecyclerView();
        return view;
    }
    private void inintRecyclerView(){

    }
}
