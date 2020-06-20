package com.example.franklin.hudumu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.franklin.hudumu.R;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.RecyclerVH>{
    Context c;
    String[] services;

    public ServicesAdapter(Context c, String[] services) {
        this.c = c;
        this.services = services;
    }

    @Override
    public RecyclerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.services,parent,false);
        return new RecyclerVH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerVH holder, int position) {
        holder.nameTxt.setText(services[position]);
    }

    @Override
    public int getItemCount() {
        return services.length;
    }

    /*
    VIEWHOLDER CLASS
     */
    public class RecyclerVH extends RecyclerView.ViewHolder {
        TextView nameTxt;

        public RecyclerVH(View itemView) {
            super(itemView);
            nameTxt= (TextView) itemView.findViewById(R.id.service_type);
        }
    }
}
