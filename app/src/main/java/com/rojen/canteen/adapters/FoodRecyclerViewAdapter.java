package com.rojen.canteen.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rojen.canteen.R;
import com.rojen.canteen.model.FoodModel;

import java.util.ArrayList;
import java.util.List;


public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.ViewHolder> {

    Context context;

    List<FoodModel> foods = new ArrayList<>();

    public FoodRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodRecyclerViewAdapter.ViewHolder holder, int position) {

        FoodModel food = foods.get(position);

        holder.itemName.setText(food.getItemName());
        holder.itemPrice.setText(food.getItemPrice());

        // load image
        Glide.with(holder.itemView).asBitmap().load(food.getItemPicture()).into(holder.itemImage);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", food.getId());
                bundle.putString("name", food.getItemName());
                bundle.putString("price", food.getItemPrice());
                bundle.putString("image", food.getItemPicture());
                bundle.putInt("quantity", food.getItemQuantity());
                bundle.putString("type", food.getItemType());
                Navigation.findNavController(holder.itemView).navigate(R.id.action_foodListFragment_to_foodDetailFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public void setFoods(List<FoodModel>  foods) {
        this.foods = foods;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private TextView itemName;
        private TextView itemPrice;
        private ImageView itemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.foodCardParent);
            itemName = itemView.findViewById(R.id.cardItemName);
            itemPrice = itemView.findViewById(R.id.cardPrice);

            itemImage = itemView.findViewById(R.id.itemImage);

        }
    }
}
