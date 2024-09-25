package com.example.luluchef.database;

import androidx.lifecycle.LiveData;

import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;

import java.util.List;

public interface PlanedMealRepoInterface {

    LiveData<List<PlanedMeal>> getPlanedMeals();
    void insertMeal(PlanedMeal meal);
    void deletePlanedMeal(PlanedMeal meal);
}
