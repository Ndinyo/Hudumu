package com.example.franklin.hudumu.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franklin.hudumu.R;

import java.util.List;

public class ContactUsAdapter extends RecyclerView.Adapter<ContactUsAdapter.ContactUsRecycler> {

    private Context mContext;
    private final String[] mStringsName;
    private final Integer[] mIntsImages;

    public ContactUsAdapter(Context context, String[] stringsName, Integer[] images) {
        this.mContext = context;
        this.mStringsName = stringsName;
        this.mIntsImages = images;
    }

    @NonNull
    @Override
    public ContactUsRecycler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.contact_us_items, viewGroup, false);
        return new ContactUsRecycler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactUsRecycler holder, int position) {
        holder.mTextView.setText(mStringsName[position]);
        holder.mImageView.setImageResource(mIntsImages[position]);
    }

    @Override
    public int getItemCount() {
        return mStringsName.length;
    }

    //@Override
    //public View getView(int i, View view, ViewGroup viewGroup) {
    //  View mContactUsRecyclerView;

    //LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    //if (view==null){
    //  mContactUsRecyclerView = new View(mContext);
            /*mContactUsRecyclerView = inflater.inflate(R.layout.contact_us_items, null);
            mTextView = (TextView) mContactUsRecyclerView.findViewById(R.id.textViewContactInfo);
            mImageView = (ImageView) mContactUsRecyclerView.findViewById(R.id.imageContactUs);

            mTextView.setText(mStringsName[i]);
            mImageView.setImageResource(mIntsImages[i]);
        }else {
            mContactUsRecyclerView = (View)view;
        }
        return mContactUsRecyclerView;
    }*/

    //ViewHolder class
    public class ContactUsRecycler extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private ImageView mImageView;

        public ContactUsRecycler(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.textViewContactInfo);
            mImageView = (ImageView) itemView.findViewById(R.id.imageContactUs);
        }
    }
}
