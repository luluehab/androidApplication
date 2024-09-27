package com.example.luluchef.favourite.view;

import com.example.luluchef.model.Meal;

import java.util.List;

public interface FavView {
    void showMeals(List<Meal> meals);
    void showErr(String error);
}
