package com.example.luluchef.favourite.Presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.luluchef.favourite.view.FavView;
import com.example.luluchef.home.view.HomeView;
import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.MealResponse;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.NetworkCallBack;

import java.util.List;

public class FavPresenter implements FavPresenterInterface {

    private final FavView view;
    private final MealRepository repo;
    LiveData<List<Meal>> favMealList;
    public FavPresenter(FavView view, MealRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void loadMeals() {
        favMealList = repo.getAllFavouriteMeals();
        favMealList.observeForever(new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                view.showMeals(meals);
            }
        });

    }

    @Override
    public void removeFromFavourite(Meal meal) {
       repo.deleteMealFromFavourite(meal);
    }


}
