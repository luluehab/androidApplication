package com.example.luluchef.details.presenter;

import com.example.luluchef.home.view.HomeView;
import com.example.luluchef.model.Meal;

public interface DetailPresenterInterface {


    public void loadMealsInDetails(String id) ;
    public void addToFavourite(Meal meal);



}
