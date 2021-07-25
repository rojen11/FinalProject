package com.rojen.canteen.repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rojen.canteen.model.FoodModel;

public class FoodRepository {

    FirebaseFirestore db;

    public void FoodRepository() {
        db = FirebaseFirestore.getInstance();

    }

    public  FoodModel getFood() {
//        db.collection("food")
//                .get()
//                .addOnCompleteListener()
    }

}
