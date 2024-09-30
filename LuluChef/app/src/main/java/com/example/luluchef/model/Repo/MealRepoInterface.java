package com.example.luluchef.model.Repo;

import androidx.lifecycle.LiveData;

import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;
import com.example.luluchef.planner.Presenter.PlanPresenterInterface;

import java.util.Date;
import java.util.List;

public interface MealRepoInterface {


    void insertMealToFavourite(Meal meal);

    void insertAllFav(List<Meal> meals);

    void deleteMealFromFavourite(Meal meal);

    void deleteAllFavouriteMeals();

    LiveData<Meal>  getFavMealById(String id);
    LiveData<PlanedMeal> getPlanMealById(String id);
    LiveData<List<Meal>> getAllFavouriteMeals();

    LiveData<List<PlanedMeal>> getAllPlannedMeals();

    LiveData<List<PlanedMeal>> getMealForDay(Date day);


    void insertMealToCalendar(PlanedMeal meal, Date day);


    void deletePlannedMeal(PlanedMeal meal);
}
