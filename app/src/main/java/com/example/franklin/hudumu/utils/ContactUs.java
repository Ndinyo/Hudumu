package com.example.franklin.hudumu.utils;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.adapters.ContactUsAdapter;

public class ContactUs extends Fragment {

    //private Activity context;
    private RecyclerView mRecyclerView;
    private String[] mStringsName = {"0791795131", "0791795131", "client@huduma.tech",
            "@Huduma", "https://www.facebook.com/Huduma", "https://www.linkedin.com-Huduma"};

    Integer[] mIntsImages = {R.drawable.phone_48, R.drawable.sms_48, R.drawable.new_post_48,
            R.drawable.twitter_50, R.drawable.facebook_48, R.drawable.linkedin_48};

    public static ContactUs newInstance(){
        ContactUs contactUs = new ContactUs();
        return contactUs;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_contact_us,null);

        //REFERENCE
        mRecyclerView= (RecyclerView) rootView.findViewById(R.id.contactUsRecyclerView);

        //LAYOUT MANAGER
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //ADAPTER
        mRecyclerView.setAdapter(new ContactUsAdapter(getActivity(),mStringsName,mIntsImages));

        return rootView;
    }

    @Override
    public String toString(){
        return "ContactUs";
    }
}
