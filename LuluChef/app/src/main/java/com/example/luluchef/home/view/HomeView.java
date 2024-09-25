package com.example.luluchef.home.view;

import com.example.luluchef.model.Meal;

import java.util.List;

public interface HomeView {
    void showMeals(List<Meal> products);
    void showErr(String error);
}
