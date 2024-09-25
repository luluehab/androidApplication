package com.example.luluchef.database;

import androidx.lifecycle.LiveData;
import com.example.luluchef.model.Meal;
import com.example.luluchef.network.RemoteSource;

import java.util.List;

public interface MealRepoInterface {
    LiveData<List<Meal>> getMeals();
    void insertMeal(Meal meal);
    void deleteMeal(Meal meal);
}
