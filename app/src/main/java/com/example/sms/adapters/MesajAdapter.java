package com.example.sms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sms.R;
import com.example.sms.models.MesajModel;

import java.util.ArrayList;


public class MesajAdapter extends RecyclerView.Adapter<MesajAdapter.ViewHolder> {
    Context context;
    ArrayList<MesajModel> mesajModel;

    public MesajAdapter(Context context, ArrayList<MesajModel> mesajModel) {
        this.context = context;
        this.mesajModel = mesajModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mesaj_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mesajModel.get(position).getName());
        holder.description.setText(mesajModel.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mesajModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_mesaj_adi);
            description = itemView.findViewById(R.id.tv_mesaj);
        }
    }
}
