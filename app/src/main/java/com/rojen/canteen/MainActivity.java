package com.rojen.canteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rojen.canteen.model.FoodModel;
import com.rojen.canteen.utils.Utils;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Utils.getInstance(this);

    }
}