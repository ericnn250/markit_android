package com.markit.ecommerce.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.markit.ecommerce.CartFragment;
import com.markit.ecommerce.IMainActivity;
import com.markit.ecommerce.R;
import com.markit.ecommerce.models.CartItem;
import com.markit.ecommerce.models.Product;
import com.markit.ecommerce.touchhelpers.ItemTouchHelperAdapter;
import com.markit.ecommerce.util.BigDecimalUtil;
import com.markit.ecommerce.util.CartManger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        ItemTouchHelperAdapter,
        GestureDetector.OnGestureListener
{

    private static final String TAG = "CartRecyclerViewAd";

    private static final int PRODUCT_TYPE = 1;
    private static final int HEADER_TYPE = 2;

    //vars
    private ArrayList<CartItem> mProducts = new ArrayList<>();
    private Context mContext;
    private ItemTouchHelper mTouchHelper;
    private GestureDetector mGestureDetector;
    private ViewHolder mSelectedHolder;
    private IMainActivity iMainActivity;
    double totalprice=0.0;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onIncreaseQuantity(int position);
        void onDecreaseQuantity(int position);

        void onRemoveItem(int position);
        void checkout(int radioid);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public CartRecyclerViewAdapter(Context context, ArrayList<CartItem> products) {
        mContext = context;
        mProducts = products;
        mGestureDetector = new GestureDetector(mContext, this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case PRODUCT_TYPE:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cart_item, parent, false);
                return new ViewHolder(view,mListener);
            case HEADER_TYPE:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_cart_section_header, parent, false);
                return new SectionHeaderViewHolder(view,mListener);
            default:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cart_item, parent, false);
                return new ViewHolder(view,mListener);
       }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);

   totalprice=totalprice + (mProducts.get(position).getProduct().getPrice().doubleValue() *mProducts.get(position).getQuantity());

        if (itemViewType == PRODUCT_TYPE) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(mProducts.get(position).getProduct().getImage())
                    .into(((ViewHolder)holder).image);

            ((ViewHolder)holder).title.setText(mProducts.get(position).getProduct().getTitle());
            ((ViewHolder)holder).price.setText(BigDecimalUtil.getValue(mProducts.get(position).getProduct().getPrice()));
            ((ViewHolder)holder).quantity.setText(String.valueOf(mProducts.get(position).getQuantity()));
             //totalprice=totalprice + (mProducts.get(position).getProduct().getPrice().doubleValue() *mProducts.get(position).getQuantity());

            ((ViewHolder)holder).parentView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    new CartFragment().setIsScrolling(false);

                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        mSelectedHolder = ((ViewHolder)holder);
                        mGestureDetector.onTouchEvent(event);
                    }

                    return true;
                }
            });
        }
        else{
            SectionHeaderViewHolder headerViewHolder = (SectionHeaderViewHolder) holder;
            headerViewHolder.sectionTitle.setText(mProducts.get(position).getProduct().getTitle());

            BigDecimal b=new BigDecimal(totalprice, MathContext.DECIMAL32);
            headerViewHolder.t_price.setText(BigDecimalUtil.getValue(b)+"\t Rwf");
        }


    }


    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    @Override
    public int getItemViewType(int position) {
            if(TextUtils.isEmpty(mProducts.get(position).getProduct().getType())){
                return HEADER_TYPE;
            }
            else{
                return PRODUCT_TYPE;
            }
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
//        Product fromProduct = mProducts.get(fromPosition);
//        Product product = new Product(fromProduct);
//        mProducts.remove(fromPosition);
//        mProducts.add(toPosition, product);
//        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemSwiped(int position) {
        CartManger cartManger = new CartManger(mContext);
        //cartManger.removeItemFromCart(mProducts.get(position));

        mProducts.remove(mProducts.get(position));
        notifyItemRemoved(position);
    }

    public void setTouchHelper(ItemTouchHelper touchHelper) {

        mTouchHelper = touchHelper;
    }


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        if(!(new CartFragment().isScrolling())){
           // mTouchHelper.startDrag(mSelectedHolder);
        }
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image,remove;
        TextView title, price,quantity;
        RelativeLayout parentView,increase_qty,decrease_qty;

        public ViewHolder(View itemView,final OnItemClickListener listener) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            quantity=itemView.findViewById(R.id.quantity);
            parentView = itemView.findViewById(R.id.parent);
            increase_qty= itemView.findViewById(R.id.increase_quantity);
            decrease_qty=itemView.findViewById(R.id.decrease_quantity);
            remove=itemView.findViewById(R.id.remove_from_cart);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);;
                        }
                    }
                }
            });
            increase_qty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onIncreaseQuantity(position);
                        }
                    }
                }
            });
            decrease_qty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDecreaseQuantity(position);
                        }
                    }
                }
            });
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onRemoveItem(position);
                        }
                    }
                }
            });
        }
    }

    public class SectionHeaderViewHolder extends RecyclerView.ViewHolder {

        TextView sectionTitle,t_price,checkout;
        RadioGroup radioGroup;
        RadioButton radioButton;

        public SectionHeaderViewHolder(View itemView,final OnItemClickListener listener) {
            super(itemView);
            sectionTitle = itemView.findViewById(R.id.cart_section_header);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            t_price= itemView.findViewById(R.id.totalprice);
            checkout= itemView.findViewById(R.id.checkout);
            checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            int radioId = radioGroup.getCheckedRadioButtonId();
                            listener.checkout(radioId);
                        }
                    }
                }
            });
        }
    }
}




