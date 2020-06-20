package com.example.franklin.hudumu.services.market;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.franklin.hudumu.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class MarketOrderFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.market_order, container, false);


        //Saving data to firebase
        //mFirebaseFirestore = FirebaseFirestore.getInstance();

        //builder = new AlertDialog.Builder(getContext());//Initialize AlertDialog



        return rootView;

    }
}
