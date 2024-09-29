package com.example.luluchef.meals.view;

import com.example.luluchef.model.Meal;

import java.util.List;

public interface mealView {
    void showDetails(List<Meal> meal);
    void showErr(String error);
}
