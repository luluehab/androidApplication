package com.example.luluchef.meals.presenter;

import com.example.luluchef.meals.view.mealView;
import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.MealResponse;
import com.example.luluchef.model.PlanedMeal;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.NetworkCallBack;

import java.util.Date;
import java.util.List;

public class mealPresenter implements mealPresenterInterface, NetworkCallBack {

    private MealRepository repo;
    private mealView view;

    public mealPresenter(MealRepository repo, mealView view) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void insertMealToFavourite(Meal meal) {
        repo.insertMealToFavourite(meal);
    }

    @Override
    public void insertMealToCalendar(PlanedMeal meal, Date day) {
        repo.insertMealToCalendar(meal, day);
    }

    @Override
    public void getCountryMeals(String country) {
            repo.getMealsByCountry(country, this);
    }

    @Override
    public void getCategoryMeals(String category) {
            repo.getMealsByCategory(category,this);
    }

    @Override
    public void onSuccessResultMeal(List<Meal> meals) {
        view.showDetails(meals);
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
    public void onFailure(String message) {
        view.showErr(message);
    }
}
