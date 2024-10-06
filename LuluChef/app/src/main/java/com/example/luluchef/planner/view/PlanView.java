package com.example.luluchef.planner.view;

import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;

import java.util.List;

public interface PlanView {
    //void showMeals(List<PlanedMeal> meals);
    void showErr(String error);
    void showDatemeal(List<PlanedMeal> meals);
}
