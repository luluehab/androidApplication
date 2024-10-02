package com.example.luluchef.search.Presenter;

import static androidx.fragment.app.FragmentManager.TAG;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.MealResponse;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.NetworkCallBack;
import com.example.luluchef.search.view.SearchView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchPresenter implements SearchPresenterInterface, NetworkCallBack {

    private SearchView view;
    private MealRepository repo ;
    LiveData<List<Meal>> MealList;
    private  List<Meal> filterMeals ;
    private List<Meal> finalMeals;
    String category;
    String country ;
    String ingredient;
    private static final String TAG = "lulu";
    public SearchPresenter(SearchView view, MealRepository repo) {
        this.view = view;
        this.repo = repo;
        this.filterMeals = new ArrayList<>();
        this.finalMeals = new ArrayList<>();
    }


    @Override
    public void getMealByName(String name, String category, String country, String ingredient) {
        this.category = category;
        this.country = country;
        this.ingredient = ingredient;
        repo.getMealByName(name , this);
    }

    @Override
    public void getMealByFirstChar(String firstChar,  String category, String country, String ingredient) {

        this.category = category;
        this.country = country;
        this.ingredient = ingredient;
        repo.getMealByFirstChar(firstChar , this);
    }

    @Override
    public List<Meal> filterMeals(List<Meal> meals, String category, String country, String ingredient) {
        // Result list to hold the filtered meals
        List<Meal> filteredMeals = new ArrayList<>();

        for (Meal meal : meals) {
            // Check category, country, and ingredient conditions
            boolean matchesCategory = (category == null || meal.getStrCategory() != null && meal.getStrCategory().equalsIgnoreCase(category));
            boolean matchesCountry = (country == null || meal.getStrArea() != null && meal.getStrArea().equalsIgnoreCase(country));
            boolean matchesIngredient = (ingredient == null ||
                    (meal.getStrIngredient1() != null && meal.getStrIngredient1().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient2() != null && meal.getStrIngredient2().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient3() != null && meal.getStrIngredient3().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient4() != null && meal.getStrIngredient4().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient5() != null && meal.getStrIngredient5().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient6() != null && meal.getStrIngredient6().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient7() != null && meal.getStrIngredient7().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient8() != null && meal.getStrIngredient8().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient9() != null && meal.getStrIngredient9().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient10() != null && meal.getStrIngredient10().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient11() != null && meal.getStrIngredient11().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient12() != null && meal.getStrIngredient12().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient13() != null && meal.getStrIngredient13().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient14() != null && meal.getStrIngredient14().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient15() != null && meal.getStrIngredient15().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient16() != null && meal.getStrIngredient16().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient17() != null && meal.getStrIngredient17().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient18() != null && meal.getStrIngredient18().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient19() != null && meal.getStrIngredient19().equalsIgnoreCase(ingredient)) ||
                    (meal.getStrIngredient20() != null && meal.getStrIngredient20().equalsIgnoreCase(ingredient)));

            // If all conditions match, add the meal to the filtered list
            if (matchesCategory && matchesCountry && matchesIngredient) {
                filteredMeals.add(meal);
            }
        }

        return filteredMeals;
    }

    @Override
    public void addToFavourite(Meal meal) {
        repo.insertMealToFavourite(meal);
    }


    @Override
    public void onSuccessResultMeal(List<Meal> meals) {
        if(meals != null) {

            view.showMeals(filterMeals(meals, category,country,ingredient));
        }
        else
        {
            view.showErr("no result");
        }
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
