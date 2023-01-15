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
import com.example.sms.models.GrupOlusturModel;

import java.util.ArrayList;

public class GrupOlusturAdapter extends RecyclerView.Adapter<GrupOlusturAdapter.ViewHolder> {
    Context context;
    ArrayList<GrupOlusturModel> grupOlusturModel;

    public GrupOlusturAdapter(Context context, ArrayList<GrupOlusturModel> grupOlusturModel) {
        this.context = context;
        this.grupOlusturModel = grupOlusturModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.grup_olustur_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(grupOlusturModel.get(position).getImg_url()).into(holder.grupImg);
        holder.name.setText(grupOlusturModel.get(position).getName());
        holder.description.setText(grupOlusturModel.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return grupOlusturModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView grupImg;
        TextView name, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            grupImg = itemView.findViewById(R.id.iv_grup);
            name = itemView.findViewById(R.id.tv_grup_name);
            description = itemView.findViewById(R.id.tv_grup_desc);
        }
    }
}
