package com.example.franklin.hudumu.services.market;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.adapters.MarketAdapter;
import com.example.franklin.hudumu.models.MarketModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VegetablesFragment extends Fragment {

    private Gson mGson;
    private int cartProductNo = 0;
    private MySharedPreference mMySharedPreference;
    private Button mButton;

    public VegetablesFragment() {
    }

    private static RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_categories_market, null);

        //REFERENCE
        mRecyclerView = (RecyclerView) view.findViewById(R.id.marketRecyclerView);

        //LAYOUT MANAGER
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<MarketModel> marketModels = getAllCateringItems();

        //ADAPTER
        mRecyclerView.setAdapter(new MarketAdapter(getActivity(), marketModels));

        mMySharedPreference = new MySharedPreference(getContext());

        //GsonBuilder gsonBuilder = new GsonBuilder();
        //mGson = gsonBuilder.create();

        mButton = (Button)view.findViewById(R.id.add_to_cart);

        return view;
    }

    private List<MarketModel> getAllCateringItems() {
        List<MarketModel> cateringModels = new ArrayList<>();

        cateringModels.add(new MarketModel(R.drawable.cabbage, "CABBAGE", "40"));
        cateringModels.add(new MarketModel(R.drawable.carrots, "CARROTS 1KG", "120"));
        cateringModels.add(new MarketModel(R.drawable.frenchbeans, "FRENCH BEANS 1KG", "150"));
        cateringModels.add(new MarketModel(R.drawable.minji, "PEAS 1KG", "220"));
        cateringModels.add(new MarketModel(R.drawable.onions, "ONIONS 1KG", "100"));
        cateringModels.add(new MarketModel(R.drawable.pepper, "PEPPER 1KG", "220"));
        cateringModels.add(new MarketModel(R.drawable.potatoes, "POTATOES 1KG", "150"));
        cateringModels.add(new MarketModel(R.drawable.sukuma_wki, "SUKUMA WIKI", "50"));
        cateringModels.add(new MarketModel(R.drawable.sweet_peppers, "SWEET PEPPER 1KG", "220"));
        cateringModels.add(new MarketModel(R.drawable.tomatoes, "TOMATOES 1KG", "160"));

        return cateringModels;
    }
}
