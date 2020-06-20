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

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.adapters.MarketAdapter;
import com.example.franklin.hudumu.models.MarketModel;

import java.util.ArrayList;
import java.util.List;

public class FruitsFragment extends Fragment {
    public FruitsFragment(){}

    private static RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_categories_market,null);

        //REFERENCE
        mRecyclerView= (RecyclerView) view.findViewById(R.id.marketRecyclerView);

        //LAYOUT MANAGER
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<MarketModel> marketModels = getAllCateringItems();

        //ADAPTER
        mRecyclerView.setAdapter(new MarketAdapter(getActivity(),marketModels));



        return view;
    }

    private List<MarketModel> getAllCateringItems() {
        List<MarketModel> cateringModels = new ArrayList<>();

        cateringModels.add(new MarketModel(R.drawable.apples,"APPLES", "25"));
        cateringModels.add(new MarketModel(R.drawable.avocado, "AVOCADO", "30"));
        cateringModels.add(new MarketModel(R.drawable.bananas,"BANANAS", "10"));
        cateringModels.add(new MarketModel(R.drawable.berries,"BERRIES 1KG", "150"));
        cateringModels.add(new MarketModel(R.drawable.lemons,"LEMONS", "10"));
        cateringModels.add(new MarketModel(R.drawable.mangoes,"MANGOES", "25"));
        cateringModels.add(new MarketModel(R.drawable.oranges,"ORANGES", "20"));
        cateringModels.add(new MarketModel(R.drawable.pawpaw,"PAWPAW", "30"));
        cateringModels.add(new MarketModel(R.drawable.watermelons,"WATERMELONS", "80"));

        return cateringModels;
    }
}
