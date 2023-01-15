package com.example.sms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sms.R;
import com.example.sms.models.MsgGonderModel;

import java.util.ArrayList;

public class MsgGonderAdapter extends RecyclerView.Adapter<MsgGonderAdapter.ViewHolder> {

    private ArrayList<MsgGonderModel> msgGonder;
    private Context context;

    public MsgGonderAdapter(Context context,ArrayList<MsgGonderModel> msgGonder) {
        this.msgGonder = msgGonder;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_gonder_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(msgGonder.get(position).getImg_url()).into(holder.msgImg);
        holder.name.setText(msgGonder.get(position).getName());
        holder.desc.setText(msgGonder.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return msgGonder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView msgImg;
        TextView name,desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            msgImg = itemView.findViewById(R.id.iv_msg_card_img);
            name = itemView.findViewById(R.id.tv_msg_card_name);
            desc = itemView.findViewById(R.id.tv_msg_card_desc);
        }
    }
}
