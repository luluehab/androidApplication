package com.example.luluchef.model.Repo;

import androidx.lifecycle.LiveData;

import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;

import java.util.Date;
import java.util.List;

public interface MealRepoInterface {


    void insertMealToFavourite(Meal meal);

    void insertAllFav(List<Meal> meals);

    void deleteMealFromFavourite(Meal meal);

    void deleteAllFavouriteMeals();

    LiveData<List<Meal>> getAllFavouriteMeals();

    LiveData<List<PlanedMeal>> getMealsOfDay(String day);


    void insertMealToCalendar(PlanedMeal meal, Date day);


    void deletePlannedMeal(PlanedMeal meal);
}
