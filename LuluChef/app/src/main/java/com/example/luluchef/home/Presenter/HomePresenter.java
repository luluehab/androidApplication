package com.example.luluchef.home.Presenter;

import com.example.luluchef.home.view.HomeView;
import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.MealResponse;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.APIClient;
import com.example.luluchef.network.NetworkCallBack;

import java.util.List;

public class HomePresenter implements HomePresenterInterface, NetworkCallBack{

    private final HomeView view;
    private final MealRepository repo;

    public HomePresenter(HomeView view, MealRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void loadMeals() {

       repo.getRandomMeal(this);

    }

    public void getAllCountries() {
        repo.getAllCountries(this);
    }
    public void getAllCategories()
    {
        repo.getAllCategories(this);
    }

    @Override
    public void addToFavourite(Meal meal) {
        repo.insertMealToFavourite(meal);
    }


    @Override
    public void onSuccessResultMeal(List<Meal> meals) {
        view.showMeals(meals);
    }

    @Override
    public void onSuccessFilter(MealResponse meals) {

    }

    @Override
    public void onSuccessResultCategory(List<Category> categories) {
        view.showCategories(categories);
    }

    @Override
    public void onSuccessResultIngredient(List<Ingredient> ingredients) {

    }

    @Override
    public void onSuccessResultCountries(List<Country> countries) {
        view.showCountries(countries);
    }

    @Override
    public void onFailure(String message) {
        view.showErr(message);
    }
}
