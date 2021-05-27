package com.markit.ecommerce;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.markit.ecommerce.models.Product;
import com.markit.ecommerce.util.BigDecimalUtil;


public class ViewProductFragment extends Fragment {


    private static final String TAG = "ViewProductFragment";

    //widgets
    public ImageView mImageView;
    private TextView mTitle;
    private TextView mPrice;

    //vars
   // public Product mProduct;
    public int mImage;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            mImage = bundle.getInt(getString(R.string.intent_product));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_product, container, false);
        mImageView = view.findViewById(R.id.image);
        mTitle = view.findViewById(R.id.title);
        mPrice = view.findViewById(R.id.price);

        setProduct();

        return view;
    }

    private void setProduct(){
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(getActivity())
                .setDefaultRequestOptions(requestOptions)
                .load(mImage)
                .into(mImageView);
//
//        mTitle.setText(mProduct.getTitle());
//        mPrice.setText(BigDecimalUtil.getValue(mProduct.getPrice()));
    }


}