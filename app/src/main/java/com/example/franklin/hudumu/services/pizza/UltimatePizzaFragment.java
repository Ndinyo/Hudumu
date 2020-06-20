package com.example.franklin.hudumu.services.pizza;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.adapters.PizzaAdapter;
import com.example.franklin.hudumu.models.PizzaModel;

import java.util.ArrayList;
import java.util.List;

public class UltimatePizzaFragment extends Fragment {
    public UltimatePizzaFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories_pizza, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.gridViewPizza);

        List<PizzaModel> pizzaModels = getAllPizzaItems();
        PizzaAdapter pizzaAdapter = new PizzaAdapter(getActivity(), pizzaModels);
        gridView.setAdapter(pizzaAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getActivity(), "Position: " + i, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private List<PizzaModel> getAllPizzaItems() {
        List<PizzaModel> pizzaModels = new ArrayList<>();

        pizzaModels.add(new PizzaModel(R.drawable.meatz, "MEATZZA", "Small", "700"));
        pizzaModels.add(new PizzaModel(R.drawable.meatz, "MEATZZA", "Medium", "900"));
        pizzaModels.add(new PizzaModel(R.drawable.meatz, "MEATZZA", "Large", "1250"));

        pizzaModels.add(new PizzaModel(R.drawable.regina, "REGINA", "Small", "700"));
        pizzaModels.add(new PizzaModel(R.drawable.regina, "REGINA", "Medium", "900"));
        pizzaModels.add(new PizzaModel(R.drawable.regina, "REGINA", "Large", "1250"));

        return pizzaModels;
    }
}
