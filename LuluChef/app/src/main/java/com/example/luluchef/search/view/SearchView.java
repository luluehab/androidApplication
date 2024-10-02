package com.example.luluchef.search.view;

import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Meal;

import java.util.List;

public interface SearchView {
    void showMeals(List<Meal> meals);
    void showErr(String error);
    void showCountries(List<Country> countries);
    void showCategories(List<Category> categories);
}
