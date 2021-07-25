package com.rojen.canteen.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rojen.canteen.model.FoodModel;

import java.util.List;

public class FoodListViewModel  extends ViewModel {

    private final MutableLiveData<List<FoodModel>> food = new MutableLiveData<List<FoodModel>>();


    private int current = 0;

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getCurrent() {
        return this.current;
    }
}
