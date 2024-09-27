package com.example.luluchef.favourite.view;

import com.example.luluchef.model.Meal;

public interface FavOnClickListener {
    void onMealItemClicked(String id);
    void onFavClicked(Meal meal);
}
