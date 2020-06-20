package com.example.franklin.hudumu.services.catering;

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
import com.example.franklin.hudumu.adapters.CateringAdapter;
import com.example.franklin.hudumu.models.CateringModel;

import java.util.ArrayList;
import java.util.List;

public class EatOutsFragment extends Fragment {

    public EatOutsFragment(){}

    private static RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_categories_catering,null);

        //REFERENCE
        mRecyclerView= (RecyclerView) view.findViewById(R.id.cateringRecyclerView);

        //LAYOUT MANAGER
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<CateringModel> cateringModels = getAllCateringItems();

        //ADAPTER
        mRecyclerView.setAdapter(new CateringAdapter(getActivity(),cateringModels));

        return view;

    }

    private List<CateringModel> getAllCateringItems() {
        List<CateringModel> cateringModels = new ArrayList<>();

        cateringModels.add(new CateringModel(R.drawable.breads,"CHEESY BREAD", "500"));
        cateringModels.add(new CateringModel(R.drawable.sideorders, "CLASSIC CHICKEN WINGS", "750"));
        cateringModels.add(new CateringModel(R.drawable.breads,"BBQ CHEESY BREAD", "650"));
        cateringModels.add(new CateringModel(R.drawable.sideorders, "PERI-PERI CHICKEN WINGS", "750"));

        return cateringModels;
    }
}
