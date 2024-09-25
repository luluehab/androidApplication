package com.example.luluchef.network;

import android.database.Observable;

import com.example.luluchef.model.CategoryResponse;
import com.example.luluchef.model.CountryResponse;
import com.example.luluchef.model.IngredientResponse;
import com.example.luluchef.model.MealResponse;


public interface RemoteSource {

    Observable<MealResponse> getMealByName(String name);

    Observable<MealResponse> getMealByFirstChar(String firstChar);

    Observable<MealResponse> getMealById(String id);

    Observable<MealResponse> getRandomMeal();

    Observable<CategoryResponse> getAllCategories();

    Observable<CountryResponse> getAllCountries();

    Observable<IngredientResponse> getAllIngredients();

    Observable<MealResponse> getMealsByIngredient(String ingredient);

    Observable<MealResponse> getMealsByCategory(String category);

    Observable<MealResponse> getMealsByCountry(String country);

    Observable<MealResponse> getRandomMeals();
}