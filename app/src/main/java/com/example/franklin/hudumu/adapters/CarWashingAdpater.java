package com.example.franklin.hudumu.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.models.CarWashingModel;
import com.example.franklin.hudumu.models.CateringModel;

import java.util.ArrayList;
import java.util.List;

public class CarWashingAdpater extends ArrayAdapter<CarWashingModel> {

    private Context mContext;
    private ArrayList<CarWashingModel> mCarWashingModels;
    private LayoutInflater mLayoutInflater;

    public static class ViewHolder{
        TextView mTextView;
        CheckBox mCheckBox;
    }

    public CarWashingAdpater(@NonNull Context context, ArrayList<CarWashingModel> carWashingModels) {
        super(context, R.layout.car_washing_items, carWashingModels);
        mContext = context;
        mCarWashingModels = carWashingModels;
    }

   /* @NonNull
    @Override
    public CarWash onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.car_washing_items, viewGroup, false);
        return new CarWash(view);
    }*/

    /*@Override
    public void onBindViewHolder(@NonNull CarWash carWash, int i) {
        carWash.mTextView.setText(mCarWashingModels.get(i).getDetails());
    }*/

    /*@Override
    public int getItemCount() {
        return mCarWashingModels.size();
    }*/

    @Override
    public int getCount() {
        return mCarWashingModels.size();
    }

    @Nullable
    @Override
    public CarWashingModel getItem(int position) {
        return mCarWashingModels.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        final View result;

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_washing_items, parent, false);
            viewHolder.mTextView = (TextView)convertView.findViewById(R.id.textViewCarWashItems);
            viewHolder.mCheckBox = (CheckBox)convertView.findViewById(R.id.checkBoxItems);


            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
            result = convertView;
        }

        CarWashingModel carWashingModel = getItem(position);

        viewHolder.mTextView.setText(carWashingModel.details);
        viewHolder.mCheckBox.setChecked(carWashingModel.isChecked);

        return result;
    }
}
