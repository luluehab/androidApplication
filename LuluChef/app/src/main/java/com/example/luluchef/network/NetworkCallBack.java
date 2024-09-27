package com.example.luluchef.network;

import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.MealResponse;
import com.example.luluchef.model.Repo.MealRepository;

import java.util.List;

public interface NetworkCallBack {
   // void onSuccess(List<T> response);
   // void onFailure(String error);


    public void onSuccessResultMeal(List<Meal> meals);
    public void onSuccessFilter(MealResponse meals);
    public void onSuccessResultCategory(List<Category> categories);
    public void onSuccessResultIngredient(List<Ingredient> ingredients);
    public void onSuccessResultCountries(List<Country> countries);
    public void onFailure(String message);
}
