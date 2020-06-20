package com.example.franklin.hudumu.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.models.CateringModel;

import java.util.List;

public class CateringAdapter extends RecyclerView.Adapter<CateringAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<CateringModel> mCateringModels;
    private Context mContext;


    public CateringAdapter(Context context, List<CateringModel> pizzaModels) {
        mCateringModels = pizzaModels;
        mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.catering_fragment_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.imageViewCatering.setImageResource(mCateringModels.get(i).getCateringImages());
        viewHolder.mTextViewPrice.setText(mCateringModels.get(i).getPrice());
        viewHolder.mTextViewDetails.setText(mCateringModels.get(i).getDetails());
    }

    @Override
    public int getItemCount() {
        return mCateringModels.size();
    }

    /*@Override
    public int getCount() {
        return mCateringModels.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            view = mLayoutInflater.inflate(R.layout.catering_fragment_list, viewGroup, false);
            viewHolder.imageViewCatering = (ImageView)view.findViewById(R.id.imageViewCatering);
            viewHolder.mTextViewDetails = (TextView)view.findViewById(R.id.textViewCateringDetail);
            viewHolder.mTextViewPrice = (TextView)view.findViewById(R.id.textViewCateringPrice);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.imageViewCatering.setImageResource(mCateringModels.get(i).getCateringImages());
        viewHolder.mTextViewPrice.setText(mCateringModels.get(i).getPrice());
        viewHolder.mTextViewDetails.setText(mCateringModels.get(i).getDetails());

        return view;
    }*/

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCatering = (ImageView) itemView.findViewById(R.id.imageViewCatering);
            mTextViewDetails = (TextView) itemView.findViewById(R.id.textViewCateringDetail);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.textViewCateringPrice);
        }

        ImageView imageViewCatering;
        TextView mTextViewPrice;
        TextView mTextViewDetails;
    }
}
