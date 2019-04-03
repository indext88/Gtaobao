package com.example.indext.gtaobao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class mytao_page_fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.mytao_page, container, false);
        Button btn_tell=view.findViewById(R.id.btn_tell);
        btn_tell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),GtaoTel.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
