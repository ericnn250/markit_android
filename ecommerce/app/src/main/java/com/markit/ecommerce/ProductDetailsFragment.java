package com.markit.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.Fade;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.markit.ecommerce.adapters.ProductPagerAdapter;
import com.markit.ecommerce.customviews.MyDragShadowBuilder;
import com.markit.ecommerce.models.Product;
import com.markit.ecommerce.resources.Products;
import com.markit.ecommerce.util.BigDecimalUtil;
import com.markit.ecommerce.util.CartManger;

import java.util.ArrayList;

public class ProductDetailsFragment extends Fragment implements
        View.OnTouchListener,
        View.OnClickListener,
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener,
        View.OnDragListener{

    private static final String TAG = "ViewProductActivity";

    //widgets
    private ViewPager mProductContainer;
    private TabLayout mTabLayout;
    private RelativeLayout mAddToCart,mCartIcon, mCart;
    private ImageView  mPlusIcon;
    private IMainActivity mInterface;
    private ImageView increase,decrease;
    private TextView quantity,cartsize,product_title,p_title,price;
    private RelativeLayout mBackArrow;

    //vars
    private Product mProduct;
    private ProductPagerAdapter mPagerAdapter;
    private GestureDetector mGestureDetector;
    private Rect mCartPositionRectangle;


    public ProductDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle=this.getArguments();
        if (bundle!=null){
            mProduct=bundle.getParcelable(getString(R.string.intent_product_datails));
        }else{
            Intent h=new Intent(getContext(),HomeActivity.class);
            startActivity(h);
            getActivity().finish();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view= inflater.inflate(R.layout.fragment_product_details, container, false);
        mProductContainer = view.findViewById(R.id.product_container);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mAddToCart = view.findViewById(R.id.add_to_cart);
        mCart = view.findViewById(R.id.cart);
        mPlusIcon = view.findViewById(R.id.plus_image);
        mCartIcon = view.findViewById(R.id.cart_image);
        increase=view.findViewById(R.id.product_view_increase_q);
        decrease=view.findViewById(R.id.product_view_decrease_q);
        quantity=view.findViewById(R.id.product_view_quantity);
        cartsize=view.findViewById(R.id.cart_count_pd);
        p_title=view.findViewById(R.id.product_view_title);
        price=view.findViewById(R.id.product_view_price);
        product_title=view.findViewById(R.id.fragment_heading_p_datails);
        mBackArrow = view.findViewById(R.id.back_arrow_product_details);
        mBackArrow.setOnClickListener(this);
        mProductContainer.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(getContext(), this);
        mCart.setOnClickListener(this);
        mAddToCart.setOnClickListener(this);
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrease_quantity();
            }
        });
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase_quantity();
            }
        });


       // getIncomingIntent();
        initPagerAdapter();
         return view;
    }

    private void decrease_quantity() {

        int q=Integer.parseInt(quantity.getText().toString());
        quantity.setText(String.valueOf(q<2?1:q-1));
    }

    private void increase_quantity() {
        int q=Integer.parseInt(quantity.getText().toString());
        quantity.setText(String.valueOf(q>1000?1000:q+1));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getCartSize();
        product_title.setText(mProduct.getTitle());
        p_title.setText(mProduct.getTitle());
        price.setText(BigDecimalUtil.getValue(mProduct.getPrice())+"\t Rwf");
    }

    private void getIncomingIntent(){
        Bundle bundle=this.getArguments();

        Intent intent;
        intent = getActivity().getIntent();
        if(intent.hasExtra(getString(R.string.intent_product))){
            mProduct = intent.getParcelableExtra(getString(R.string.intent_product));
        }
    }

    private void initPagerAdapter(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        //Products products = new Products();
        //Product[] selectedProducts = products.PRODUCT_MAP.get(mProduct.getType());
        ArrayList<Integer> image=new ArrayList<>();
        if (mProduct.getImage()!=0){
            image.add(mProduct.getImage());
        }
        if (mProduct.getPhoto1()!=0){
            image.add(mProduct.getPhoto1());
        }
        if (mProduct.getPhoto2()!=0){
            image.add(mProduct.getPhoto2());
        }
        if (mProduct.getPhoto3()!=0){
            image.add(mProduct.getPhoto3());
        }
        if (mProduct.getPhoto4()!=0){
            image.add(mProduct.getPhoto4());
        }
        if (mProduct.getPhoto5()!=0){
            image.add(mProduct.getPhoto5());
        }
        if (mProduct.getPhoto6()!=0){
            image.add(mProduct.getPhoto6());
        }
        if (mProduct.getPhoto7()!=0){
            image.add(mProduct.getPhoto7());
        }
        if (mProduct.getPhoto8()!=0){
            image.add(mProduct.getPhoto8());
        }
        if (mProduct.getPhoto9()!=0){
            image.add(mProduct.getPhoto9());
        }
        if (mProduct.getPhoto10()!=0){
            image.add(mProduct.getPhoto10());
        }

        for(int product: image){
            Bundle bundle = new Bundle();
            bundle.putInt(getString(R.string.intent_product), product);
            ViewProductFragment viewProductFragment = new ViewProductFragment();
            viewProductFragment.setArguments(bundle);
            fragments.add(viewProductFragment);
        }
        mPagerAdapter = new ProductPagerAdapter(getFragmentManager(), fragments);
        mProductContainer.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mProductContainer, true);
    }



    private void getCartPosition(){
        mCartPositionRectangle = new Rect();
        mCart.getGlobalVisibleRect(mCartPositionRectangle);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        mCartPositionRectangle.left = mCartPositionRectangle.left - Math.round((int)(width * 0.18));
        mCartPositionRectangle.top = 0;
        mCartPositionRectangle.right = width;
        mCartPositionRectangle.bottom = mCartPositionRectangle.bottom - Math.round((int)(width * 0.03));
    }

    private void setDragMode(boolean isDragging){
        if(isDragging){
            mCartIcon.setVisibility(View.INVISIBLE);
            mPlusIcon.setVisibility(View.VISIBLE);
        }
        else{
            mCartIcon.setVisibility(View.VISIBLE);
            mPlusIcon.setVisibility(View.INVISIBLE);
        }
    }


    private void addCurrentItemToCart(){
        int q=Integer.parseInt(quantity.getText().toString());
       // Product selectedProduct = ((ViewProductFragment)mPagerAdapter.getItem(mProductContainer.getCurrentItem())).mProduct;
        CartManger cartManger = new CartManger(getContext());
        cartManger.addItemToCart(mProduct,q);
        Toast.makeText(getContext(), "added to cart", Toast.LENGTH_SHORT).show();
        getCartSize();
    }

    private void inflateFullScreenProductFragment(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        FullScreenProductFragment fragment = new FullScreenProductFragment();

        Bundle bundle = new Bundle();
       int selectedProduct =((ViewProductFragment)mPagerAdapter.getItem(mProductContainer.getCurrentItem())).mImage;
        bundle.putInt(getString(R.string.intent_product), selectedProduct);
        fragment.setArguments(bundle);

        // Enter Transition for New Fragment
        Fade enterFade = new Fade();
        enterFade.setStartDelay(1);
        enterFade.setDuration(300);
        fragment.setEnterTransition(enterFade);

        transaction.addToBackStack(getString(R.string.fragment_full_screen_product));
        transaction.replace(R.id.full_screen_container, fragment, getString(R.string.fragment_full_screen_product));
        transaction.commit();
    }



    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.cart:{
                //open Cart Activity
//                getFragmentManager().beginTransaction()
//                        .remove(ProductDetailsFragment.this).commit();
//                HomeActivity homeActivity=(HomeActivity)getActivity();
//                homeActivity.viewcart();
                Intent intent = new Intent(getContext(), ViewCartActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.add_to_cart:{
                addCurrentItemToCart();
                break;
            }
            case R.id.back_arrow_product_details:{
                Log.d(TAG, "onClick: navigating back.");
                mInterface.onBackPressed();
            }
        }

    }


    /*
        OnTouch
     */

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        getCartPosition();

        if(view.getId() == R.id.product_container){
            mGestureDetector.onTouchEvent(motionEvent);
        }

//        int action = motionEvent.getAction();
//
//        switch(action) {
//            case (MotionEvent.ACTION_DOWN):
//                Log.d(TAG, "Action was DOWN");
//                return false;
//            case (MotionEvent.ACTION_MOVE):
//                Log.d(TAG, "Action was MOVE");
//                return false;
//            case (MotionEvent.ACTION_UP):
//                Log.d(TAG, "Action was UP");
//                return false;
//            case (MotionEvent.ACTION_CANCEL):
//                Log.d(TAG, "Action was CANCEL");
//                return false;
//            case (MotionEvent.ACTION_OUTSIDE):
//                Log.d(TAG, "Movement occurred outside bounds " +
//                        "of current screen element");
//                return false;
//        }

        return false;
    }

    /*
        GestureDetector
     */

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d(TAG, "onDown: called");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.d(TAG, "onShowPress: called.");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.d(TAG, "onSingleTapUp: called.");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent,
                            MotionEvent motionEvent1,
                            float v, float v1) {
        Log.d(TAG, "onScroll: called.");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.d(TAG, "onLongPress: called.");

        ViewProductFragment fragment = ((ViewProductFragment)mPagerAdapter.getItem(mProductContainer.getCurrentItem()));
        // Instantiates the drag shadow builder.
        View.DragShadowBuilder myShadow = new MyDragShadowBuilder(
                ((ViewProductFragment)fragment).mImageView, fragment.mImage);

        // Starts the drag
        ((ViewProductFragment)fragment).mImageView.startDrag(null,  // the data to be dragged
                myShadow,  // the drag shadow builder
                null,      // no need to use local data
                0          // flags (not currently used, set to 0)
        );

        myShadow.getView().setOnDragListener(this);
    }

    @Override
    public boolean onFling(MotionEvent motionEvent,
                           MotionEvent motionEvent1,
                           float v, float v1) {
        Log.d(TAG, "onFling: called.");
        return false;
    }

    /*
        DoubleTap
     */

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        Log.d(TAG, "onSingleTapConfirmed: called.");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Log.d(TAG, "onDoubleTap: called.");
        inflateFullScreenProductFragment();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        Log.d(TAG, "onDoubleTapEvent: called.");
        return false;
    }

    /*
        OnDragListener
     */
    @Override
    public boolean onDrag(View view, DragEvent event) {

        switch(event.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                Log.d(TAG, "onDrag: drag started.");

                setDragMode(true);

                return true;

            case DragEvent.ACTION_DRAG_ENTERED:

                return true;

            case DragEvent.ACTION_DRAG_LOCATION:

                Point currentPoint = new Point(Math.round(event.getX()), Math.round(event.getY()));
//                Log.d(TAG, "onDrag: x: " + currentPoint.x + ", y: " + currentPoint.y );

                if(mCartPositionRectangle.contains(currentPoint.x, currentPoint.y)){
                    mCart.setBackgroundColor(this.getResources().getColor(R.color.blue2));
                }
                else{
                    mCart.setBackgroundColor(this.getResources().getColor(R.color.blue1));
                }

                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                return true;

            case DragEvent.ACTION_DROP:

                Log.d(TAG, "onDrag: dropped.");

                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                Log.d(TAG, "onDrag: ended.");

                Drawable background = mCart.getBackground();
                if (background instanceof ColorDrawable) {
                    if (((ColorDrawable) background).getColor() == getResources().getColor(R.color.blue2)) {
                        addCurrentItemToCart();
                    }
                }
                mCart.setBackground(this.getResources().getDrawable(R.drawable.blue_onclick_dark));
                setDragMode(false);
                return true;

            // An unknown action type was received.
            default:
                Log.e(TAG,"Unknown action type received by OnStartDragListener.");
                break;

        }
        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInterface = (IMainActivity) getActivity();
    }

    public void getCartSize(){
        CartManger cartManger=new CartManger(getContext());
        int size=cartManger.getCartItems().size();
        cartsize.setText(String.valueOf(size));
    }

    @Override
    public void onResume() {
        super.onResume();
        getCartSize();
    }
}