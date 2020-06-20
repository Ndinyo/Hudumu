package com.example.franklin.hudumu.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.models.ExpandableListViewData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Faq extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);



        expandableListView = (ExpandableListView) findViewById(R.id.expandable_listView);
        expandableListInfo = (ExpandableListViewData.populateData());
        expandableListTitle = new ArrayList<String>(expandableListInfo.keySet());
        expandableListAdapter = new com.example.franklin.hudumu.adapters.ExpandableListAdapter(this, expandableListTitle, expandableListInfo);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                //Toast.makeText(getApplicationContext(), expandableListTitle.get(i) + "List Expanded ", Toast.LENGTH_SHORT).show();
            }
        });


        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                //Toast.makeText(getApplicationContext(), expandableListTitle.get(i) + "List Collapsed ", Toast.LENGTH_SHORT).show();
            }
        });


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                //Toast.makeText(getApplicationContext(), expandableListTitle.get(i) + " -> "
                  //      + expandableListInfo.get(expandableListTitle.get(i)).get(i1), Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }
}
