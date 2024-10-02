package com.example.luluchef.search.Filter.presenter;

import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.MealResponse;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.NetworkCallBack;
import com.example.luluchef.search.Filter.view.FilterView;

import java.util.List;

public class FilterPresenter implements FilterPresenterInterface , NetworkCallBack {

    FilterView view;
    MealRepository repo;

    public FilterPresenter(FilterView view, MealRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void getCategories() {
        repo.getAllCategories(this);
    }

    @Override
    public void getCountries() {
        repo.getAllCountries(this);
    }

    @Override
    public void getIngredients() {
        repo.getAllIngredients(this);
    }

    @Override
    public void onSuccessResultMeal(List<Meal> meals) {

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
        view.showIngrediants(ingredients);
    }

    @Override
    public void onSuccessResultCountries(List<Country> countries) {
            view.showCountries(countries);
    }

    @Override
    public void onFailure(String message) {

    }
}
