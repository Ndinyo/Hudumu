package com.example.franklin.hudumu.services.carwashing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.adapters.CarWashingAdpater;
import com.example.franklin.hudumu.models.CarWashingModel;

import java.util.ArrayList;

public class VIPWashFragment extends Fragment {


    private ListView mListView;
    CarWashingAdpater mCarWashingAdpater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_carwash_super, container, false);

        //REFERENCE
        mListView = (ListView) rootView.findViewById(R.id.listViewSuperWash);

        //LAYOUT MANAGER
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final ArrayList<CarWashingModel> carWashingModels = getAllItems();

        //ADAPTER
        mListView.setAdapter(new CarWashingAdpater(getContext(), carWashingModels));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CarWashingModel model = carWashingModels.get(i);
                model.isChecked = !model.isChecked;
                CarWashingAdpater adpater = new CarWashingAdpater(getContext(), carWashingModels);
                adpater.notifyDataSetChanged();
            }
        });

        return rootView;
    }

    private ArrayList<CarWashingModel> getAllItems() {
        ArrayList<CarWashingModel> models = new ArrayList<>();

        models.add(new CarWashingModel("Headliner/Roof", true));
        models.add(new CarWashingModel("Dashboard & Vents", true));
        models.add(new CarWashingModel("Floor Mats", true));
        models.add(new CarWashingModel("Body & Wheels", true));
        models.add(new CarWashingModel("Door Trim", true));
        models.add(new CarWashingModel("Safety Belts", true));
        models.add(new CarWashingModel("Engine Bay", true));
        models.add(new CarWashingModel("FULL - SIZE SUVS (KSH. 2,500.00)", false));
        models.add(new CarWashingModel("TRUCKS (KSH. 5,000.00)", false));
        models.add(new CarWashingModel("BUSES (KSH. 5,000.00)", false));
        models.add(new CarWashingModel("VANS (KSH. 2,500.00)", false));
        models.add(new CarWashingModel("SALOON CARS (KSH. 1,200.00)", false));

        return models;
    }

}
