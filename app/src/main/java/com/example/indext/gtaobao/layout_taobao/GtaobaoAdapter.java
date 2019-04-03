package com.example.indext.gtaobao.layout_taobao;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.indext.gtaobao.Interface.Main2Activity;
import com.example.indext.gtaobao.R;

import java.util.List;

public class GtaobaoAdapter extends RecyclerView.Adapter<GtaobaoAdapter.ViewHolder>{

    private List<Gtaobao> mGtaobaoList;
    private  Context context;
    private LayoutInflater li = null;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View GtaobaoView;
        ImageView GtaobaoImage;
        TextView GtaobaoName;
        TextView Name2;
        TextView Name3;
        TextView Name4;
        TextView Name5;

        public ViewHolder(View view) {
            super(view);
            GtaobaoView = view;
            GtaobaoImage = (ImageView) view.findViewById(R.id.img_g);
            GtaobaoName = (TextView) view.findViewById(R.id.txt_g1);
            Name2=(TextView)view.findViewById(R.id.txt_g2);
            Name3=(TextView)view.findViewById(R.id.txt_g3);
            Name4=(TextView)view.findViewById(R.id.txt_g4);
            Name5=(TextView)view.findViewById(R.id.txt_g5);

        }

    }



    public GtaobaoAdapter(List<Gtaobao> gtaobaoList) {
        mGtaobaoList = gtaobaoList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gtaobao_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Gtaobao gtaobao = mGtaobaoList.get(position);
                Toast.makeText(v.getContext(), gtaobao.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(),Main2Activity.class);
                intent.putExtra("name",gtaobao.getName());
                intent.putExtra("img",gtaobao.getImageId());
                v.getContext().startActivity(intent);
            }
        });

        holder.GtaobaoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Gtaobao gtaobao = mGtaobaoList.get(position);
                Toast.makeText(v.getContext(), "图片的单击"+gtaobao.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Gtaobao gtaobao = mGtaobaoList.get(position);
        holder.GtaobaoImage.setImageResource(gtaobao.getImageId());
        holder.GtaobaoName.setText(gtaobao.getName());
        holder.Name2.setText(gtaobao.getName2());
        holder.Name3.setText(gtaobao.getName3());
        holder.Name4.setText(gtaobao.getName4());
        holder.Name5.setText(gtaobao.getName5());
    }

    @Override
    public int getItemCount() {
        return mGtaobaoList.size();
    }


}



