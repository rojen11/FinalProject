package com.rojen.canteen.viewmodels;

import androidx.lifecycle.ViewModel;

public class FoodListViewModel  extends ViewModel {
    private int current = 0;

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getCurrent() {
        return this.current;
    }
}
