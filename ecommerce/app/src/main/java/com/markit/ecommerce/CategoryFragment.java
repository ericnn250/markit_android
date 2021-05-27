package com.markit.ecommerce;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class CategoryFragment extends Fragment implements View.OnClickListener{
    private RelativeLayout mBackArrow;
    private IMainActivity mInterface;
    private static final String TAG = "ViewCategoryFragment";


    public CategoryFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_category, container, false);
        mBackArrow = view.findViewById(R.id.back_arrow_category);
        mBackArrow.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.back_arrow_category){
            Log.d(TAG, "onClick: navigating back.");
            mInterface.onBackPressed();
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInterface = (IMainActivity) getActivity();
    }
}