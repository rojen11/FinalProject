package com.rojen.canteen.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rojen.canteen.R;
import com.rojen.canteen.model.FoodModel;


import java.util.ArrayList;
import java.util.List;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder> {


    List<FoodModel> cartList = new ArrayList<>();

    Context context;

    public CartRecyclerViewAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("Food", "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartRecyclerViewAdapter.ViewHolder holder, int position) {
        FoodModel food = cartList.get(position);
        holder.cartItemName.setText(food.getItemName());
        holder.cartItemPrice.setText("Rs. " + food.getItemPrice());

        Glide.with(holder.itemView).asBitmap().load(food.getItemPicture()).into(holder.cartItemImage);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void setCartList(ArrayList<FoodModel> cartList){
        this.cartList = cartList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View parent;
        private TextView cartItemName;
        private TextView cartItemPrice;

        private ImageView cartItemImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cartItemName = itemView.findViewById(R.id.cartItemName);
            cartItemPrice = itemView.findViewById(R.id.cartItemPrice);
            cartItemImage = itemView.findViewById(R.id.cartImage);
        }
    }
}