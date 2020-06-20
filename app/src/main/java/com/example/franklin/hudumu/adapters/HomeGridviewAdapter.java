package com.example.franklin.hudumu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franklin.hudumu.R;

public class HomeGridviewAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] mStringsName = {"Errands","Edit Profile","Payment plans",
            "My History", "FAQ","Contact Us"};

    private final int[] mIntsImages = {R.drawable.girl_shopping_cart_new,R.drawable.icons8_user80,R.drawable.icons8_online_payment,
            R.drawable.icons8_order_history128,R.drawable.icons8_faq,R.drawable.icons8_phone96};

    private TextView mTextView;
    private ImageView mImageView;

    public HomeGridviewAdapter(Context c) {
        mContext = c;
        //this.mIntsImages = mIntsImages;
        //this.mStringsName = mStringsName;
    }

    public int getCount() {
        return mStringsName.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View gridViewTextImage;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view==null){
            gridViewTextImage = new View(mContext);
            gridViewTextImage = inflater.inflate(R.layout.gridview_layout, null);
            mTextView = (TextView) gridViewTextImage.findViewById(R.id.gridview_textview);
            mImageView = (ImageView) gridViewTextImage.findViewById(R.id.gridview_image);

            mTextView.setText(mStringsName[i]);
            mImageView.setImageResource(mIntsImages[i]);
        }else {
            gridViewTextImage = (View)view;
        }
        return gridViewTextImage;
    }
}
