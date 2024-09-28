package com.example.luluchef.home.view;

import com.example.luluchef.model.Meal;

public interface HomeOnClickListener {
    void onMealItemClicked(String id);
    void onFavClicked(Meal meal);
    void onCalClicked(Meal meal);
}
