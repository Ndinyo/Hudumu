package com.example.franklin.hudumu.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.models.MarketModel;

import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<MarketModel> mMarketModels;
    private Context mContext;

    public MarketAdapter(Context context, List<MarketModel> marketModels ) {
        mMarketModels = marketModels;
        mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.market_fragment_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.imageViewMarket.setImageResource(mMarketModels.get(i).getMarketImages());
        viewHolder.mTextViewPrice.setText(mMarketModels.get(i).getPrice());
        viewHolder.mTextViewDetails.setText(mMarketModels.get(i).getDetails());
    }

    @Override
    public int getItemCount() {
        return mMarketModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewMarket = (ImageView)itemView.findViewById(R.id.imageViewMarket);
            mTextViewDetails = (TextView)itemView.findViewById(R.id.textViewMarketDetail);
            mTextViewPrice = (TextView)itemView.findViewById(R.id.textViewMarketPrice);
        }

        ImageView imageViewMarket;
        TextView mTextViewPrice;
        TextView mTextViewDetails;
    }
}
