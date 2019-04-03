package com.example.indext.gtaobao.Interface;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.indext.gtaobao.R;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter <MsgAdapter.ViewHolder>{
    private List<Msg>mMsglist;
    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
        ImageView imageView;
        public ViewHolder(View view){
            super(view);
            leftLayout=(LinearLayout)view.findViewById(R.id.left_layout);
            rightLayout=(LinearLayout)view.findViewById(R.id.right_layout);
            leftMsg=(TextView)view.findViewById(R.id.left_msg);
            rightMsg=(TextView)view.findViewById(R.id.right_msg);
            imageView=view.findViewById(R.id.img_1);
        }
    }
    public MsgAdapter(List<Msg>msgList){
        mMsglist=msgList;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Msg msg=mMsglist.get(position);
        holder.imageView.setImageResource(msg.getImg_view());
        //创建弹出菜单
        //右删除菜单
        final PopupMenu popupMenu_right=new PopupMenu(holder.itemView.getContext(),holder.rightMsg);
        popupMenu_right.inflate(R.menu.del_menu);
        popupMenu_right.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                holder.rightLayout.setVisibility(View.GONE);
                Main2Activity.mDaoSession.getMsgDao().delete(msg);
                mMsglist.remove(position);
                return false;
            }
        });
        holder.rightMsg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                popupMenu_right.show();
                return false;
            }
        });
        //左删除菜单
        final PopupMenu popupMenu_left=new PopupMenu(holder.itemView.getContext(),holder.leftMsg);
        popupMenu_left.inflate(R.menu.del_menu);
        popupMenu_left.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                holder.leftLayout.setVisibility(View.GONE);
                Main2Activity.mDaoSession.getMsgDao().delete(msg);
                mMsglist.remove(position);
                return false;
            }
        });
        holder.leftMsg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                popupMenu_left.show();
                return false;
            }
        });



        if(msg.getType()==Msg.TYPE_RECEIVED){
//            如果是收到消息，则显示左边消息布局，隐藏右边消息布局
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());


        }else if(msg.getType()==Msg.TYPE_SENT) {
//            如果是发出消息，则显示右边的消息布局，隐藏左边的消息布局
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(msg.getContent());

        }
    }
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        return  new ViewHolder(view);
    }
    @Override
    public int getItemCount(){
        return mMsglist.size();
    }
}
