package com.rojen.canteen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rojen.canteen.adapters.CartRecyclerViewAdapter;
import com.rojen.canteen.model.FoodModel;
import com.rojen.canteen.utils.Utils;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartListFragment newInstance(String param1, String param2) {
        CartListFragment fragment = new CartListFragment();
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
        View view = inflater.inflate(R.layout.fragment_cart_list, container, false);

        // elements
        RecyclerView cartRecView = (RecyclerView) view.findViewById(R.id.cartRecView);
        ImageButton backButton = view.findViewById(R.id.backButton2);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        Utils utils = Utils.getInstance();

        ArrayList<FoodModel> carts = utils.getCart();

        CartRecyclerViewAdapter cartRecyclerViewAdapter = new CartRecyclerViewAdapter(requireContext());

        cartRecView.setAdapter(cartRecyclerViewAdapter);
        cartRecView.setLayoutManager(new GridLayoutManager(getActivity(), 1));


        Log.d("Food", String.valueOf(carts.size()));

        cartRecyclerViewAdapter.setCartList(carts);


        return  view;
    }
}