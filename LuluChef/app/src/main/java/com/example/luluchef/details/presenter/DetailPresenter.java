package com.example.luluchef.details.presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.luluchef.details.view.DetailsView;
import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.MealResponse;
import com.example.luluchef.model.PlanedMeal;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.NetworkCallBack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailPresenter implements DetailPresenterInterface , NetworkCallBack {



    private final DetailsView view;
    private final MealRepository repo;
    private String id ;
    private String from;
    LiveData<Meal> favMeal;
    LiveData<PlanedMeal> planMeal;
    public DetailPresenter(DetailsView view , MealRepository repo) {
        this.view = view;
        this.repo = repo;
    }


    @Override
    public void loadMealsInDetails(String id , String from) {
        this.id = id;
        this.from = from;
        repo.getMealById(id , this);
    }

    @Override
    public void loadMealFavDatabase(String id) {
        favMeal =repo.getFavMealById(id);
        favMeal.observeForever(new Observer<Meal>() {
            @Override
            public void onChanged(Meal meals) {
                view.showDetails(meals);
            }
        });
    }

    @Override
    public void loadMealPlanDatabase(String id) {

        planMeal = repo.getPlanMealById(id);
        planMeal.observeForever(new Observer<PlanedMeal>() {
            @Override
            public void onChanged(PlanedMeal planedMeal) {
                view.showDetails(planedMeal.getMeal());
            }
        });

    }


    @Override
    public void addToFavourite(Meal meal) {
        repo.insertMealToFavourite(meal);
    }




    @Override
    public void onSuccessResultMeal(List<Meal> meals) {
        view.showDetails(meals.get(0));
    }

    @Override
    public void onSuccessFilter(MealResponse meals) {

    }

    @Override
    public void onSuccessResultCategory(List<Category> categories) {

    }

    @Override
    public void onSuccessResultIngredient(List<Ingredient> ingredients) {

    }

    @Override
    public void onSuccessResultCountries(List<Country> countries) {

    }

    @Override
    public void onFailure(String error) {

        if (from == "Plan")
        {
            loadMealPlanDatabase(id);
        }
        else if(from == "Fav")
        {
            loadMealFavDatabase(id);
        }
            view.showErr(error);

    }
}
