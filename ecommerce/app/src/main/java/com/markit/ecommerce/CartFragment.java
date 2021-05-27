package com.markit.ecommerce;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.markit.ecommerce.adapters.CartRecyclerViewAdapter;
import com.markit.ecommerce.models.CartItem;
import com.markit.ecommerce.models.Product;
import com.markit.ecommerce.resources.ProductHeaders;
import com.markit.ecommerce.util.CartManger;

import java.math.BigDecimal;
import java.util.ArrayList;


public class CartFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ViewCartActivity";

    //widgets
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;

    //vars
private CartRecyclerViewAdapter mAdapter;
    //private CartAdapter mAdapter;
    private ArrayList<CartItem> mProducts = new ArrayList<>();
    private boolean mIsScrolling;

    public CartFragment() {
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_fr);
        mFab = view.findViewById(R.id.fab);

        mFab.setOnClickListener(this);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getProducts();
        initRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        //mProducts.clear();
//        getProducts();
//        initRecyclerView();
    }

    private void getProducts() {
        //add the headers
//        mProducts.add(new Product(ProductHeaders.HEADER_TITLES[0], 0, "", new BigDecimal(0), 0));
//        mProducts.add(new Product(ProductHeaders.HEADER_TITLES[1], 0, "", new BigDecimal(0), 0));
//        mProducts.add(new Product(ProductHeaders.HEADER_TITLES[2], 0, "", new BigDecimal(0), 0));

        CartManger cartManger = new CartManger(getContext());
        mProducts.addAll(cartManger.getCartItems());
        mProducts.add(new CartItem(new Product(ProductHeaders.HEADER_TITLES[2], 0, "", new BigDecimal(0), 0),3));
    }

    private void initRecyclerView() {
        mAdapter = new CartRecyclerViewAdapter(getContext(), mProducts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

//        ItemTouchHelper.Callback callback = new CartItemTouchHelperCallback(mAdapter);
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
//        mAdapter.setTouchHelper(itemTouchHelper);
       // itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.setAdapter(mAdapter);

        //wait for the recyclerview to finish loading the views
        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                    mRecyclerView.setOnScrollListener(new CartScrollListener());
                } else {
                    mRecyclerView.addOnScrollListener(new CartScrollListener());
                }
            }
        });
    }


    private void setFABVisibility(boolean isVisible) {
        Animation animFadeOut = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out);
        Animation animFadeIn = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in);
        if (isVisible) {
            mFab.setAnimation(animFadeIn);
            mFab.setVisibility(View.VISIBLE);
        } else {
            mFab.setAnimation(animFadeOut);
            mFab.setVisibility(View.INVISIBLE);
        }
    }

    public boolean isRecyclerScrollable() {
        return mRecyclerView.computeVerticalScrollRange() > mRecyclerView.getHeight();
    }

    public void setIsScrolling(boolean isScrolling) {
        mIsScrolling = isScrolling;
    }

    public boolean isScrolling() {
        return mIsScrolling;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.fab) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    class CartScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                Log.d(TAG, "onScrollStateChanged: stopped...");
            }
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                Log.d(TAG, "onScrollStateChanged: fling.");
            }
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                Log.d(TAG, "onScrollStateChanged: touched.");
            }
            setIsScrolling(true);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (isRecyclerScrollable()) {
                if (!recyclerView.canScrollVertically(1)) {
                    setFABVisibility(true);
                } else {
                    setFABVisibility(false);
                }
            }
            setIsScrolling(true);
        }
    }
}