package com.example.luluchef.database;

import androidx.lifecycle.LiveData;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;

import java.util.Date;
import java.util.List;

public interface LocalSourceInterface {
    void insertMeal(Meal meal);

    void insertAllFav(List<Meal> meals);

    void deleteMeal(Meal meal);

    void deleteAllMeals() ;

    LiveData<List<Meal>> getAllMeals();
    LiveData<List<PlanedMeal>> getAllPlannedMeals();
    LiveData<List<PlanedMeal>> getMealForDay(Date day);
    void insertMealToCalendar(PlanedMeal meal , Date date);

    LiveData<Meal> getMealById(String id);
    LiveData<PlanedMeal> getPlanMealById(String id);



    void deletePlannedMeal(PlanedMeal meal);
}
