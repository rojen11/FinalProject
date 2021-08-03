package com.rojen.canteen.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rojen.canteen.model.FoodModel;
import com.rojen.canteen.repository.FoodRepository;

import java.util.ArrayList;
import java.util.List;

public class FoodListViewModel  extends ViewModel {

    private MutableLiveData<List<FoodModel>> food = new MutableLiveData<>();
    private MutableLiveData<List<FoodModel>> allFood = new MutableLiveData<>();
    private int current = 0;
    private FoodRepository foodRepository;
    private String[] category = {"alltime", "breakfast", "lunch", "snack", "dinner"};

    public FoodListViewModel() {
        foodRepository = new FoodRepository();
        allFood = foodRepository.getFood();
    }

    public void setCurrentFood() {
        String type = category[this.current];
        if (allFood.getValue() != null) {
            List<FoodModel> newList = new ArrayList<>();
            for (FoodModel f: allFood.getValue()) {
                if (f.getItemType() != null) {
                    if (f.getItemType().equals(type)) {
                        newList.add(f);
                    }
                }
            }

            food.setValue(newList);
        }
    }

    public void setCurrent(int current) {
        this.current = current;
        setCurrentFood();
    }

    public int getCurrent() {
        return this.current;
    }

    public MutableLiveData<List<FoodModel>> getFood() {
        return this.food;
    }

    public MutableLiveData<List<FoodModel>> getAllFood() {
        return this.allFood;
    }
}
