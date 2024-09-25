package com.example.luluchef.home.Presenter;

import com.example.luluchef.database.MealRepository;
import com.example.luluchef.home.view.HomeView;
import com.example.luluchef.model.Meal;

public interface HomePresenterInterface {

    public void loadMeals() ;
    public void addToFavourite(Meal meal);


}
