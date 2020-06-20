package com.example.franklin.hudumu.paymentplan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.adapters.PaymentPlansAdapter;
import com.example.franklin.hudumu.models.PaymentPlansModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentPlans extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_plans);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListViewPlans);
        expandableListInfo = (PaymentPlansModel.populateData());
        expandableListTitle = new ArrayList<String>(expandableListInfo.keySet());

        expandableListAdapter = new PaymentPlansAdapter(this, expandableListTitle, expandableListInfo);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {

            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                return false;
            }
        });

    }
}
