package com.markit.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.markit.ecommerce.adapters.CartRecyclerViewAdapter;
import com.markit.ecommerce.models.CartItem;
import com.markit.ecommerce.models.Product;
import com.markit.ecommerce.resources.ProductHeaders;
import com.markit.ecommerce.touchhelpers.CartItemTouchHelperCallback;
import com.markit.ecommerce.util.CartManger;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ViewCartActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ViewCartActivity";

    //widgets
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    private RelativeLayout mBackArrow;

    //vars
    CartRecyclerViewAdapter mAdapter;
    private ArrayList<CartItem> mProducts = new ArrayList<>();
    private boolean mIsScrolling;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        mRecyclerView = findViewById(R.id.recycler_view);
        mFab = findViewById(R.id.fab);

        mFab.setOnClickListener(this);
        mBackArrow = findViewById(R.id.back_arrow_cart);
        mBackArrow.setOnClickListener(this);

        getProducts();
        initRecyclerView();
    }

    private void getProducts(){
        //add the headers
//        mProducts.add(new Product(ProductHeaders.HEADER_TITLES[0], 0, "", new BigDecimal(0), 0));
//        mProducts.add(new Product(ProductHeaders.HEADER_TITLES[1], 0, "", new BigDecimal(0), 0));

        CartManger cartManger = new CartManger(this);
       mProducts.addAll(cartManger.getCartItems());
       mProducts.add(new CartItem(new Product(ProductHeaders.HEADER_TITLES[2], 0, "", new BigDecimal(0), 0),0));
    }

    private void initRecyclerView(){
        if (mProducts.size()==1){

            finish();
            Toast.makeText(this, "Nothing in cart try to add item", Toast.LENGTH_SHORT).show();
        }
        mAdapter = new CartRecyclerViewAdapter(this, mProducts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

//        ItemTouchHelper.Callback callback = new CartItemTouchHelperCallback(mAdapter);
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
//        mAdapter.setTouchHelper(itemTouchHelper);
//        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.setAdapter(mAdapter);

        //wait for the recyclerview to finish loading the views
        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.M){
                    mRecyclerView.setOnScrollListener(new CartScrollListener());
                }
                else{
                    mRecyclerView.addOnScrollListener(new CartScrollListener());
                }
            }
        });
        mAdapter.setOnItemClickListener(new CartRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onIncreaseQuantity(int position) {
                CartManger cartManger = new CartManger(ViewCartActivity.this);
                cartManger.increase(mProducts.get(position).getProduct(),1);
               mProducts.clear();
                getProducts();
                initRecyclerView();
                mAdapter.notifyDataSetChanged();


            }

            @Override
            public void onDecreaseQuantity(int position) {
                CartManger cartManger = new CartManger(ViewCartActivity.this);
                cartManger.decrease(mProducts.get(position).getProduct(),1);
                mProducts.clear();
                getProducts();
                initRecyclerView();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onRemoveItem(int position) {
                CartManger cartManger = new CartManger(ViewCartActivity.this);
                cartManger.removeItemFromCart(mProducts.get(position).getProduct());
                //initRecyclerView();
                mProducts.remove(mProducts.get(position));
                if (mProducts.size()==1){
                    finish();
                    Toast.makeText(ViewCartActivity.this, "Nothing in cart try to add item", Toast.LENGTH_SHORT).show();
                }
                mProducts.clear();
                getProducts();
                initRecyclerView();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void checkout(int radioid) {
                if (radioid== R.id.deliveratdoorstep){
                    Intent ch=new Intent(ViewCartActivity.this,AddressActivity.class);
                    startActivity(ch);
                }
                if (radioid== R.id.pickatstore){
                    Intent ch=new Intent(ViewCartActivity.this,CheckoutActivity.class);
                    startActivity(ch);
                }

              //
            }
        });

    }


    private void setFABVisibility(boolean isVisible){
        Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        if(isVisible){
            mFab.setAnimation(animFadeIn);
            mFab.setVisibility(View.VISIBLE);
        }
        else{
            mFab.setAnimation(animFadeOut);
            mFab.setVisibility(View.INVISIBLE);
        }
    }

    public boolean isRecyclerScrollable() {
        return mRecyclerView.computeVerticalScrollRange() > mRecyclerView.getHeight();
    }

    public void setIsScrolling(boolean isScrolling){
        mIsScrolling = isScrolling;
    }

    public boolean isScrolling(){
        return mIsScrolling;
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.fab){
            mRecyclerView.smoothScrollToPosition(0);
        }
        if (view.getId()==R.id.back_arrow_cart){
            finish();

        }
        //if (view.getId()==R.id.ch)
    }

    class CartScrollListener extends RecyclerView.OnScrollListener{

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

            if(isRecyclerScrollable()){
                if(!recyclerView.canScrollVertically(1)){
                    setFABVisibility(true);
                }
                else{
                    setFABVisibility(false);
                }
            }
            setIsScrolling(true);
        }
    }

}
