package com.rojen.canteen.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rojen.canteen.model.FoodModel;

import java.util.ArrayList;
import java.util.List;

public class FoodRepository {

    MutableLiveData<List<FoodModel>> foodList;
    FirebaseFirestore db;

    public FoodRepository() {
        db = FirebaseFirestore.getInstance();
        foodList = new MutableLiveData<>();
    }

    public MutableLiveData<List<FoodModel>> getFood() {
        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<FoodModel> foods = new ArrayList<>();
                            for (QueryDocumentSnapshot document: task.getResult()) {
                                if (document != null) {
                                    foods.add(document.toObject(FoodModel.class));
                                }
                            }
                            foodList.postValue(foods);
                            Log.d("Food", "success");
                        }

                    }
                });
        return foodList;
    }

}
