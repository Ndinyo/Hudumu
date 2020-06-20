package com.example.franklin.hudumu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.models.PizzaModel;

import java.util.List;

public class PizzaAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<PizzaModel> mPizzaModels;
    private Context mContext;

    public PizzaAdapter(Context context, List<PizzaModel> pizzaModels) {
        mPizzaModels = pizzaModels;
        mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mPizzaModels.size();
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

        ViewHolder pizzaViewHolder;
        if (view == null) {
            pizzaViewHolder = new ViewHolder();
            view = mLayoutInflater.inflate(R.layout.pizza_fragment_list, viewGroup, false);
            pizzaViewHolder.imageViewPizza = (ImageView) view.findViewById(R.id.imageViewPizza);
            pizzaViewHolder.mTextViewType = (TextView) view.findViewById(R.id.textViewPizzaType);
            pizzaViewHolder.mTextViewPrice = (TextView) view.findViewById(R.id.textViewPizzaPrice);
            pizzaViewHolder.mTextViewSize = (TextView) view.findViewById(R.id.textViewPizzaSize);

            view.setTag(pizzaViewHolder);
        } else {
            pizzaViewHolder = (ViewHolder) view.getTag();
        }
        pizzaViewHolder.imageViewPizza.setImageResource(mPizzaModels.get(i).getPizzaImages());
        pizzaViewHolder.mTextViewType.setText(mPizzaModels.get(i).getType());
        pizzaViewHolder.mTextViewSize.setText(mPizzaModels.get(i).getSize());
        pizzaViewHolder.mTextViewPrice.setText(mPizzaModels.get(i).getPrice());

        return view;
    }

    public static class ViewHolder {
        ImageView imageViewPizza;
        TextView mTextViewSize;
        TextView mTextViewPrice;
        TextView mTextViewType;
    }
}
