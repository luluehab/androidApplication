package com.example.luluchef.search.Filter.view;

import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.Meal;

import java.util.List;

public interface FilterView {
    void showMeals(List<Meal> meals);
    void showErr(String error);
    void showCountries(List<Country> countries);
    void showCategories(List<Category> categories);
    void showIngrediants(List<Ingredient> ingredients);
}
