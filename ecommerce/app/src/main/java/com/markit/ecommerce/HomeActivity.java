package com.markit.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.markit.ecommerce.models.FragmentTag;
import com.markit.ecommerce.models.Product;
import com.markit.ecommerce.models.User;
import com.markit.ecommerce.resources.Products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.markit.ecommerce.util.PreferenceKeys;

public class HomeActivity extends AppCompatActivity implements IMainActivity, BottomNavigationViewEx.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "MainActivity";

    //constants
    private static final int HOME_FRAGMENT = 0;
    private static final int CATEGORY_FRAGMENT = 1;
    //private static final int CART_FRAGMENT = 2;
    private static final int PROFILE_FRAGMENT = 2;


    //Fragments
    private HomeFragment mHomeFragment;
    private CategoryFragment mCategoryFragment;
   // private CartFragment mCartFragment;
    private ViewProfileFragment mViewProfileFragment;
    private ProductDetailsFragment mProductDetailsFragment;

    //widgets
    private BottomNavigationViewEx mBottomNavigationViewEx;
    private ImageView mHeaderImage;
    //private DrawerLayout mDrawerLayout;

    //vars
    private ArrayList<String> mFragmentsTags = new ArrayList<>();
    private ArrayList<FragmentTag> mFragments = new ArrayList<>();
    private int mExitCount = 0;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Handle navigation view item clicks here.
        switch (item.getItemId()) {


            case R.id.home: {
                mFragmentsTags.clear();
                mFragmentsTags = new ArrayList<>();
                init();
                break;
            }


            case R.id.bottom_nav_profile: {
                Log.d(TAG, "onNavigationItemSelected: Agreement.");
                if (mViewProfileFragment == null) {
                    mViewProfileFragment = new ViewProfileFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mViewProfileFragment, getString(R.string.tag_fragment_view_profile));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_view_profile));
                    mFragments.add(new FragmentTag(mViewProfileFragment, getString(R.string.tag_fragment_view_profile)));
                }
                else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_view_profile));
                    mFragmentsTags.add(getString(R.string.tag_fragment_view_profile));
                }
                setFragmentVisibilities(getString(R.string.tag_fragment_view_profile));
                item.setChecked(true);
                break;
            }

            case R.id.bottom_nav_home: {
                Log.d(TAG, "onNavigationItemSelected: HomeFragment.");

                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mHomeFragment, getString(R.string.tag_fragment_home));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_home));
                    mFragments.add(new FragmentTag(mHomeFragment, getString(R.string.tag_fragment_home)));
                }
                else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_home));
                    mFragmentsTags.add(getString(R.string.tag_fragment_home));
                }
                setFragmentVisibilities(getString(R.string.tag_fragment_home));
                item.setChecked(true);
                break;
            }

            case R.id.bottom_nav_category: {
                Log.d(TAG, "onNavigationItemSelected: CategoryFragment.");

                if (mCategoryFragment == null) {
                    mCategoryFragment = new CategoryFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mCategoryFragment, getString(R.string.tag_fragment_category));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_category));
                    mFragments.add(new FragmentTag(mCategoryFragment, getString(R.string.tag_fragment_category)));
                }
                else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_category));
                    mFragmentsTags.add(getString(R.string.tag_fragment_category));
                }
                setFragmentVisibilities(getString(R.string.tag_fragment_category));
                item.setChecked(true);
                break;
            }

//            case R.id.bottom_nav_cart: {
//               viewcart();
//                break;
//            }
        }
        //mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

   public void viewcart(){
//       Log.d(TAG, "onNavigationItemSelected: MessagesFragment.");
//       if ( mCartFragment == null) {
//           mCartFragment = new CartFragment();
//           FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//           transaction.add(R.id.main_content_frame,  mCartFragment, getString(R.string.tag_fragment_cart));
//           transaction.commit();
//           mFragmentsTags.add(getString(R.string.tag_fragment_cart));
//           mFragments.add(new FragmentTag( mCartFragment, getString(R.string.tag_fragment_cart)));
//       }
//       else {
//           mFragmentsTags.remove(getString(R.string.tag_fragment_cart));
//           mFragmentsTags.add(getString(R.string.tag_fragment_cart));
//       }
//       setFragmentVisibilities(getString(R.string.tag_fragment_cart));
//       Menu menu = mBottomNavigationViewEx.getMenu();
//       MenuItem item=menu.findItem(R.id.bottom_nav_cart);
//       item.setChecked(true);
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mBottomNavigationViewEx = findViewById(R.id.bottom_nav_view);
//        NavigationView navigationView = findViewById(R.id.navigation_view);
//        View headerView = navigationView.getHeaderView(0);
       // mHeaderImage = headerView.findViewById(R.id.header_image);
       // mDrawerLayout = findViewById(R.id.drawer_layout);

        isFirstLogin();
        //setHeaderImage();
        initBottomNavigationView();
       // setNavigationViewListener();
        init();
    }

    private void init(){
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_content_frame, mHomeFragment, getString(R.string.tag_fragment_home));
            transaction.commit();
            mFragmentsTags.add(getString(R.string.tag_fragment_home));
            mFragments.add(new FragmentTag(mHomeFragment, getString(R.string.tag_fragment_home)));
        }
        else {
            mFragmentsTags.remove(getString(R.string.tag_fragment_home));
            mFragmentsTags.add(getString(R.string.tag_fragment_home));
        }
        setFragmentVisibilities(getString(R.string.tag_fragment_home));
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        int backStackCount = mFragmentsTags.size();
        if(backStackCount > 1){
            String topFragmentTag = mFragmentsTags.get(backStackCount - 1);

            String newTopFragmentTag = mFragmentsTags.get(backStackCount - 2);
            if(newTopFragmentTag.equals(getString(R.string.tag_fragment_home))){
                mHomeFragment.getCartSize();
            }
            setFragmentVisibilities(newTopFragmentTag);

            mFragmentsTags.remove(topFragmentTag);

            mExitCount = 0;
        }
        else if( backStackCount == 1){
            String topFragmentTag = mFragmentsTags.get(backStackCount - 1);
            if(topFragmentTag.equals(getString(R.string.tag_fragment_home))){
                mHomeFragment.getCartSize();
                mHomeFragment.scrollToTop();
                mExitCount++;
                Toast.makeText(this, "1 more click to exit", Toast.LENGTH_SHORT).show();
            }
            else{
                mExitCount++;
                Toast.makeText(this, "1 more click to exit", Toast.LENGTH_SHORT).show();
            }
        }

        if(mExitCount >= 2){
            super.onBackPressed();
        }

    }

//    @Override
//    public void oncartIncrease(int number) {
//
//    }

    private void setNavigationIcon(String tagname) {
        Menu menu = mBottomNavigationViewEx.getMenu();
        MenuItem menuItem = null;
        if (tagname.equals(getString(R.string.tag_fragment_home))) {
            Log.d(TAG, "setNavigationIcon: home fragment is visible");
            menuItem = menu.getItem(HOME_FRAGMENT);
            menuItem.setChecked(true);
        }
        else if (tagname.equals(getString(R.string.tag_fragment_category))){
            Log.d(TAG, "setNavigationIcon: Category fragment is visible");
            menuItem = menu.getItem(CATEGORY_FRAGMENT);
            menuItem.setChecked(true);
        }
//        else if (tagname.equals(getString(R.string.tag_fragment_cart))) {
//            Log.d(TAG, "setNavigationIcon: cart fragment is visible");
//            menuItem = menu.getItem(CART_FRAGMENT);
//            menuItem.setChecked(true);
//        }
        else if (tagname.equals(getString(R.string.tag_fragment_view_profile))) {
            Log.d(TAG, "setNavigationIcon: profile fragment is visible");
            menuItem = menu.getItem(PROFILE_FRAGMENT);
            menuItem.setChecked(true);
        }
    }

    private void setFragmentVisibilities(String tagname){
        if(tagname.equals(getString(R.string.tag_fragment_home)))
            showBottomNavigation();
        else if(tagname.equals(getString(R.string.tag_fragment_category)))
            showBottomNavigation();
//        else if(tagname.equals(getString(R.string.tag_fragment_cart)))
//            showBottomNavigation();
        else if(tagname.equals(getString(R.string.tag_fragment_view_profile)))
            showBottomNavigation();
        else if(tagname.equals(getString(R.string.tag_fragment_product_details)))
            hideBottomNavigation();
//        else if(tagname.equals(getString(R.string.tag_fragment_view_profile)))
//            hideBottomNavigation();

//        else if(tagname.equals(getString(R.string.tag_fragment_agreement)))
//            hideBottomNavigation();

        for(int i = 0; i < mFragments.size(); i++){
            if(tagname.equals(mFragments.get(i).getTag())){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.show((mFragments.get(i).getFragment()));
                transaction.commit();
            }
            else{
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.hide((mFragments.get(i).getFragment()));
                transaction.commit();
            }
        }
        setNavigationIcon(tagname);

      //  printBackStack();
    }



    private void hideBottomNavigation() {
        if (mBottomNavigationViewEx != null) {
            mBottomNavigationViewEx.setVisibility(View.GONE);
        }
    }

    private void showBottomNavigation() {
        if (mBottomNavigationViewEx != null) {
            mBottomNavigationViewEx.setVisibility(View.VISIBLE);
        }
    }

//    private void setNavigationViewListener() {
//        Log.d(TAG, "setNavigationViewListener: initializing navigation drawer onclicklistener.");
//        NavigationView navigationView = findViewById(R.id.navigation_view);
//        navigationView.setNavigationItemSelectedListener(this);
//    }

    private void initBottomNavigationView() {
        Log.d(TAG, "initBottomNavigationView: initializing bottom navigation view.");
        mBottomNavigationViewEx.enableAnimation(false);
        mBottomNavigationViewEx.setOnNavigationItemSelectedListener(this);
    }

    private void isFirstLogin() {
//        Log.d(TAG, "isFirstLogin: checking if this is the first login.");
//
//        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        boolean isFirstLogin = preferences.getBoolean(PreferenceKeys.FIRST_TIME_LOGIN, true);
//
//        if (isFirstLogin) {
//            Log.d(TAG, "isFirstLogin: launching alert dialog.");
//
//            // launch the info dialog for first-time-users
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//           // alertDialogBuilder.setMessage(getString(R.string.first_time_user_message));
//            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    Log.d(TAG, "onClick: closing dialog");
//                    // now that the user has logged in, save it to shared preferences so the dialog won't
//                    // pop up again
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putBoolean(PreferenceKeys.FIRST_TIME_LOGIN, false);
//                    editor.commit();
//                    dialogInterface.dismiss();
//                }
//            });
//            alertDialogBuilder.setTitle(" ");
//            alertDialogBuilder.setIcon(R.drawable.applogo);
//            AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.show();
//        }
    }

    @Override
    public void inflateViewProfileFragment(User user) {

        if(mViewProfileFragment != null){
            getSupportFragmentManager().beginTransaction().remove(mViewProfileFragment).commitAllowingStateLoss();
        }
        mViewProfileFragment = new ViewProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.intent_user), user);
        mViewProfileFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_content_frame, mViewProfileFragment, getString(R.string.tag_fragment_view_profile));
        transaction.commit();
        mFragmentsTags.add(getString(R.string.tag_fragment_view_profile));
        mFragments.add(new FragmentTag(mViewProfileFragment, getString(R.string.tag_fragment_view_profile)));

        setFragmentVisibilities(getString(R.string.tag_fragment_view_profile));
    }

    @Override
    public void onProductSelected(Product product) {
        if (mProductDetailsFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(mProductDetailsFragment).commitAllowingStateLoss();
        }
            mProductDetailsFragment = new ProductDetailsFragment();
        Bundle args=new Bundle();
        args.putParcelable(getString(R.string.intent_product_datails),product);
        mProductDetailsFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_content_frame, mProductDetailsFragment, getString(R.string.tag_fragment_product_details));
            transaction.commit();
            mFragmentsTags.add(getString(R.string.tag_fragment_product_details));
            mFragments.add(new FragmentTag(mProductDetailsFragment, getString(R.string.tag_fragment_product_details)));
             setFragmentVisibilities(getString(R.string.tag_fragment_product_details));
    }

}