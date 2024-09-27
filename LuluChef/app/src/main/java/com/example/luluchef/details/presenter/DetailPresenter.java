package com.example.luluchef.details.presenter;

import com.example.luluchef.details.view.DetailsView;
import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.MealResponse;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.APIClient;
import com.example.luluchef.network.NetworkCallBack;

import java.util.List;

public class DetailPresenter implements DetailPresenterInterface , NetworkCallBack {
    private final DetailsView view;
    private final MealRepository repo;
    public DetailPresenter(DetailsView view , MealRepository repo) {
        this.view = view;
        this.repo = repo;
    }


    @Override
    public void loadMealsInDetails(String id) {

        repo.getMealById(id , this);

    }



    @Override
    public void addToFavourite(Meal meal) {

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
        view.showErr(error);
    }
}
