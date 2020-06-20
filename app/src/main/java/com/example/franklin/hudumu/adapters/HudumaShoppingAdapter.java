package com.example.franklin.hudumu.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.models.HudumaModel;

import java.util.ArrayList;

public class HudumaShoppingAdapter extends ArrayAdapter<HudumaModel> {
    private ArrayList<HudumaModel> mArrayList;
    Context mContext;

    //View lookup cache
    private static class ViewHolder{
        TextView mTextViewItem;
        CheckBox mCheckBox;
    }

    public HudumaShoppingAdapter(ArrayList<HudumaModel> arrayList,  Context context) {
        super(context, R.layout.shopping_items, arrayList);
        this.mArrayList = arrayList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }


    @Override
    public HudumaModel getItem(int position) {
        return mArrayList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        final View result;

        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_items, parent, false);
            viewHolder.mTextViewItem = (TextView)convertView.findViewById(R.id.textViewItems);
            viewHolder.mCheckBox = (CheckBox)convertView.findViewById(R.id.checkBoxItems);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        HudumaModel hudumaModel = getItem(position);

        viewHolder.mTextViewItem.setText(hudumaModel.item);
        viewHolder.mCheckBox.setChecked(hudumaModel.checked);

        return result;
    }
}
