package com.example.franklin.hudumu.services;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.adapters.ServicesAdapter;

public class ServicesFragment extends Fragment {
    private RecyclerView rv;
    private static String[] services={"Shopping","Pizza Ordering","Market","Dry cleaning","Car washing","Carpet cleaning"};


    public static ServicesFragment newInstance()
    {
        ServicesFragment servicesFragment = new ServicesFragment();
        return servicesFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.hudumu_services_recyclerview,null);

        //REFERENCE
        rv= (RecyclerView) rootView.findViewById(R.id.services_huduma_recyclerview);

        //LAYOUT MANAGER
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        //ADAPTER
        rv.setAdapter(new ServicesAdapter(getActivity(),services));

        return rootView;
    }

    @Override
    public String toString() {
        return "Services";
    }
}
