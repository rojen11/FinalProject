package com.rojen.canteen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.rojen.canteen.adapters.FoodRecyclerViewAdapter;
import com.rojen.canteen.viewmodels.FoodListViewModel;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LinearLayout categoryButtons;
    FoodListViewModel model;

    public FoodListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodListFragment newInstance(String param1, String param2) {
        FoodListFragment fragment = new FoodListFragment();
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
        View view =  inflater.inflate(R.layout.fragment_food_list, container, false);

        // Profile logout
        ImageView profileImage = view.findViewById(R.id.profileImage);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Logout")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(requireActivity(), LoginActivity.class);
                                startActivity(intent);
                                requireActivity().finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
            }

        });


        categoryButtons = view.findViewById(R.id.categoryButtons);

        model = new ViewModelProvider(requireActivity()).get(FoodListViewModel.class);


        // Start from first category
        setCurrent(0);


        // Category button click events
        for (int i = 0; i < categoryButtons.getChildCount(); ++i) {
            // Get single button
            Button button = (Button) categoryButtons.getChildAt(i);
            int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Change active button
                    setCurrent(finalI);
                }
            });
        }


        RecyclerView foodRecView = view.findViewById(R.id.foodRecView);

        FoodRecyclerViewAdapter foodRecyclerViewAdapter = new FoodRecyclerViewAdapter(getContext());

        foodRecView.setAdapter(foodRecyclerViewAdapter);

        foodRecView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        return view;
    }

    // set category with given index
    private void setCurrent(int i) {
        Button button = (Button) categoryButtons.getChildAt(i);
        Button prevButton = (Button) categoryButtons.getChildAt(model.getCurrent());
        prevButton.setBackgroundColor(getResources().getColor(R.color.white));
        prevButton.setTextColor(getResources().getColor(R.color.primary));

        button.setBackgroundColor(getResources().getColor(R.color.primary));
        button.setTextColor(getResources().getColor(R.color.white));

        model.setCurrent(i);

    }
}