package com.example.luluchef.search.Presenter;

import com.example.luluchef.model.Meal;

import java.util.List;

public interface SearchPresenterInterface {

    void getMealByName(String name ,  String category, String country, String ingredient);
    void getMealByFirstChar(String firstChar,  String category, String country, String ingredient);
    List<Meal> filterMeals(List<Meal> meals , String category, String country, String ingredient);
    void addToFavourite(Meal meal);

}
