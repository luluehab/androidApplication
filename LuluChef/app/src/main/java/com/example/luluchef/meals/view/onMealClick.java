package com.example.luluchef.meals.view;

import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;

public interface onMealClick {

    void onSaveBtnClicked(Meal meal);
    void onMealItemClicked(String id);
    void onCalBtnClicked(Meal meal);
}
