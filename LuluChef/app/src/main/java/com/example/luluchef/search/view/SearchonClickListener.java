package com.example.luluchef.search.view;

import com.example.luluchef.model.Meal;

public interface SearchonClickListener {
    void onMealItemClicked(String id);
    void onFavClicked(Meal meal);
    void onCalClicked(Meal meal);
    void onSearchClick(Meal meal);


}
