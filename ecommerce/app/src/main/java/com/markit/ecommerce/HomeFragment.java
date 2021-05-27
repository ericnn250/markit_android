package com.markit.ecommerce;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.markit.ecommerce.adapters.MainRecyclerViewAdapter;
import com.markit.ecommerce.models.CartItem;
import com.markit.ecommerce.models.Product;
import com.markit.ecommerce.resources.Products;
import com.markit.ecommerce.util.CartManger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class HomeFragment extends Fragment  implements
        View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener
{

    private static final String TAG = "MainActivity";
    private static final int NUM_COLUMNS = 2;

    //vars
    MainRecyclerViewAdapter mAdapter;
    private ArrayList<Product> mProducts = new ArrayList<>();

    //widgets
    private RecyclerView mRecyclerView;
    private RelativeLayout mCart;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView cartsize;
    private EditText searchEditText;



    public HomeFragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mCart = view.findViewById(R.id.cart);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        cartsize=view.findViewById(R.id.cart_count_home);
        searchEditText=view.findViewById(R.id.searcheditext);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mCart.setOnClickListener(this);

        getProducts();
        initRecyclerView();
        hideSoftKeyboard();
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId== EditorInfo.IME_ACTION_SEARCH || actionId==EditorInfo.IME_ACTION_DONE|| event.getAction()==KeyEvent.ACTION_DOWN
                        || event.getAction()==KeyEvent.KEYCODE_ENTER){

                    //the execute search
                    searchitem();


                }
                return false;
            }
        });
    }

    private void searchitem() {
        hideSoftKeyboard();
        String searchString = searchEditText.getText().toString();
        Toast.makeText(getContext(), searchString+ "is found see it next daay", Toast.LENGTH_SHORT).show();
        //perform search here
        mProducts.clear();
        //mProducts.addAll() create search list result of poduct found

        initRecyclerView();
    }

    private void hideSoftKeyboard(){
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
    }

    private void getProducts(){
        mProducts.addAll(Arrays.asList(Products.FEATURED_PRODUCTS));
    }

    private void initRecyclerView(){
        mAdapter = new MainRecyclerViewAdapter(getContext(), mProducts);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), NUM_COLUMNS);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.cart:{
                //open Cart Activity
                Intent intent = new Intent(getContext(), ViewCartActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
      //  Toast.makeText(getContext(), " Home resumed", Toast.LENGTH_SHORT).show();
        getCartSize();
    }

    @Override
    public void onRefresh() {
        Collections.shuffle(mProducts);
        onItemsLoadComplete();
    }
    public void scrollToTop(){
        mRecyclerView.smoothScrollToPosition(0);
    }
    void onItemsLoadComplete() {
        (mRecyclerView.getAdapter()).notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }
    public void getCartSize(){
        CartManger cartManger=new CartManger(getContext());
        int size=cartManger.getCartItems().size();
        cartsize.setText(String.valueOf(size));
    }
}