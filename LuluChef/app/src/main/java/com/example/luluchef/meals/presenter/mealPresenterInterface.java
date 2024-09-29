package com.example.luluchef.meals.presenter;

import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;

import java.util.Date;

public interface mealPresenterInterface {
    void insertMealToFavourite(Meal meal);
    void insertMealToCalendar(PlanedMeal meal , Date day);
    void getCountryMeals(String country);
    void getCategoryMeals(String category);
}
