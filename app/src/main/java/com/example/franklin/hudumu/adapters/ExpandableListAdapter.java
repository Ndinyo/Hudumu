package com.example.franklin.hudumu.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.franklin.hudumu.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListInfo;

    public ExpandableListAdapter(Context context, List<String> expandableListTitle, HashMap<String, List<String>> expandableListInfo) {
        mContext = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListInfo = expandableListInfo;
    }

    @Override
    public Object getChild(int listPos, int expandedListPos) {
        return this.expandableListInfo.get(this.expandableListTitle.get(listPos))
                .get(expandedListPos);
    }

    @Override
    public long getChildId(int listPos, int expandedListPos) {
        return expandedListPos;
    }

    @Override
    public View getChildView(int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        final String expandedListDetails = (String) getChild(i, i1);

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_items, null);
        }
        TextView expandedListTextView = (TextView) view.findViewById(R.id.expandable_listItem);
        expandedListTextView.setText(expandedListDetails);

        return view;
    }

    @Override
    public int getChildrenCount(int i) {
        return this.expandableListInfo.get(this.expandableListTitle.get(i)).size();
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public Object getGroup(int i) {
        return this.expandableListTitle.get(i);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        String listTitle = (String) getGroup(i);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_collection, null);
        }
        TextView listTitleTextView = (TextView) view.findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return view;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
