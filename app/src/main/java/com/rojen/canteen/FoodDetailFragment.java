package com.rojen.canteen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rojen.canteen.model.FoodModel;
import com.rojen.canteen.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FoodDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodDetailFragment newInstance(String param1, String param2) {
        FoodDetailFragment fragment = new FoodDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_detail, container, false);

        TextView itemName = view.findViewById(R.id.detailItemName);
        ImageView itemImage = view.findViewById(R.id.detailItemImage);
        TextView itemPrice = view.findViewById(R.id.detailItemPrice);
        Button addToCart = view.findViewById(R.id.buttonAddToCart);
        ImageButton backButton = view.findViewById(R.id.backButton);


        Utils utils = Utils.getInstance(getContext());


        assert getArguments() != null;
        itemName.setText(getArguments().getString("name"));
        itemPrice.setText(getString(R.string.detail_price, getArguments().getString("price"))) ;

        String imageUrl = getArguments().getString("image");

        // disable button if already in cart
        if (utils.searchCart(getArguments().getInt("id"))) {
            addToCart.setEnabled(false);
        }

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = utils.addToCart(new FoodModel(getArguments().getString("name"),getArguments().getString("price"), getArguments().getInt("quantity"), getArguments().getString("image"), getArguments().getString("type"), getArguments().getInt("id")));
                if (success) {
                    Toast.makeText(requireContext(), "Successfully added to the cart.", Toast.LENGTH_LONG).show();
                    addToCart.setEnabled(false);
                } else {
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });


        // Load image
        Glide.with(view).asBitmap().load(imageUrl).into(itemImage);

        return view;
    }
}