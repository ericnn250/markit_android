package com.markit.ecommerce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.markit.ecommerce.IMainActivity;
import com.markit.ecommerce.R;
import com.markit.ecommerce.models.Product;
import com.markit.ecommerce.util.BigDecimalUtil;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MainRecyclerViewAdapter  extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MainRecyclerViewAd";

    //vars
    private ArrayList<Product> mProducts = new ArrayList<>();
    private Context mContext;
    private IMainActivity iMainActivity;

    public MainRecyclerViewAdapter(Context context, ArrayList<Product> products) {
        mContext = context;
        mProducts = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_feed_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(mProducts.get(position).getImage())
                .into(holder.image);
        holder.ordersnum.setText("122 orders");
        holder.ratingnum.setText("(3.8)");
        holder.price.setText(BigDecimalUtil.getValue(mProducts.get(position).getPrice())+"\t Rwf");
        holder.ratingBarFeed.setRating((float) 3.8);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iMainActivity.onProductSelected(mProducts.get(position));
//                Intent intent = new Intent(mContext, ViewProductActivity.class);
//                intent.putExtra(mContext.getString(R.string.intent_product), mProducts.get(position));
//                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        iMainActivity=(IMainActivity)mContext;
    }
    @Override
    public int getItemCount() {
        return mProducts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView price,ratingnum,ordersnum;
        RatingBar ratingBarFeed;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.card_view);
            ratingBarFeed=itemView.findViewById(R.id.ratingBar);
            price=itemView.findViewById(R.id.feedprice);
            ratingnum=itemView.findViewById(R.id.revnum);
            ordersnum=itemView.findViewById(R.id.feedorders);
        }
    }
}
