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
import com.example.sms.models.RehberModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RehberAdapter extends RecyclerView.Adapter<RehberAdapter.ViewHolder>{
    Context context;
    ArrayList<RehberModel> rehberModel;

    public RehberAdapter(Context context, ArrayList<RehberModel> rehberModel) {
        this.context = context;
        this.rehberModel = rehberModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rehber_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(rehberModel.get(position).getImg_url()).into(holder.rehberImage);
        holder.name.setText(rehberModel.get(position).getName());
        holder.numara.setText(rehberModel.get(position).getNumara());
    }

    @Override
    public int getItemCount() {
        return rehberModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView rehberImage;
        TextView name, numara;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rehberImage = itemView.findViewById(R.id.iv_rehber);
            name = itemView.findViewById(R.id.tv_rehber_name);
            numara = itemView.findViewById(R.id.tv_rehber_numara);
        }
    }
}
