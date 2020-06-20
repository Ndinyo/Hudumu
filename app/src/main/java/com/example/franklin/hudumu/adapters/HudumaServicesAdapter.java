package com.example.franklin.hudumu.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franklin.hudumu.R;

public class HudumaServicesAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] services;
    private final Integer[] images;

    public HudumaServicesAdapter( Activity context, String[] services, Integer[] images) {
        super(context, R.layout.shopping_list, services);
        this.context = context;
        this.services = services;
        this.images = images;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.shopping_list, null, true);

        TextView mServices = (TextView)rowView.findViewById(R.id.textViewServices);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.iconService);

        mServices.setText(services[position]);
        imageView.setImageResource(images[position]);

        return rowView;
    }
}
