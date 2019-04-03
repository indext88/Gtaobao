package com.example.indext.gtaobao;


import android.content.Intent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.indext.gtaobao.Interface.Main2Activity;
import com.example.indext.gtaobao.Interface.Msg;

import java.util.List;

public class InAdapter extends RecyclerView.Adapter<InAdapter.ViewHolder> {
    private List<Msg> mInterList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View InterView;
        ImageView InterImage;
        TextView Name1;
        TextView Name2;
        TextView Name3;

        public ViewHolder(View view) {
            super(view);
            InterView = view;
            InterImage = (ImageView) view.findViewById(R.id.information_img);
            Name1 = (TextView) view.findViewById(R.id.information_t1);
            Name2=(TextView)view.findViewById(R.id.information_t2);
            Name3=(TextView)view.findViewById(R.id.information_t3);

        }
    }

    public InAdapter(List<Msg> interList) {
        mInterList = interList;
    }

    @Override
    public InAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.infromation_item, parent, false);
        final InAdapter.ViewHolder holder = new InAdapter.ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Msg intaobao = mInterList.get(position);
                Toast.makeText(v.getContext(), intaobao.getName1(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(),Main2Activity.class);
                intent.putExtra("name",intaobao.getName1());
                intent.putExtra("name2",intaobao.getName3());
                intent.putExtra("img",intaobao.getImageViewId());
                v.getContext().startActivity(intent);
            }
        });

        holder.InterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Msg intaobao = mInterList.get(position);
                Toast.makeText(v.getContext(), "图片的单击"+intaobao.getName1(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(InAdapter.ViewHolder holder, int position) {
        Msg intaobao = mInterList.get(position);
        holder.InterImage.setImageResource(intaobao.getImageViewId());
        holder.Name1.setText(intaobao.getName1());
        holder.Name2.setText(intaobao.getName2());
        holder.Name3.setText(intaobao.getName3());

    }

    @Override
    public int getItemCount() {
        return mInterList.size();
    }


}
