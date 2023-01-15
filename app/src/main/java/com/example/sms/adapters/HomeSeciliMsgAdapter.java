package com.example.sms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sms.R;
import com.example.sms.models.HomeSeciliMsgModel;

import java.util.ArrayList;

public class HomeSeciliMsgAdapter extends RecyclerView.Adapter<HomeSeciliMsgAdapter.ViewHolder> {
    Context context;
    ArrayList<HomeSeciliMsgModel> homeSeciliMsgModel;

    public HomeSeciliMsgAdapter(Context context, ArrayList<HomeSeciliMsgModel> homeSeciliMsgModel) {
        this.context = context;
        this.homeSeciliMsgModel = homeSeciliMsgModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_secili_msg_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(homeSeciliMsgModel.get(position).getName());
        holder.description.setText(homeSeciliMsgModel.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return homeSeciliMsgModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_home_sm_name);
            description = itemView.findViewById(R.id.tv_home_sm_desc);
        }
    }
}
