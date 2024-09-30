package com.example.luluchef.model.Repo;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.luluchef.database.LocalSource;
import com.example.luluchef.home.Presenter.HomePresenter;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;
import com.example.luluchef.network.APIClient;
import com.example.luluchef.network.NetworkCallBack;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MealRepository implements MealRepoInterface  {


    private static MealRepository instance = null;
    private final LocalSource localSource;
    private final APIClient apiClient;
    private final ExecutorService executorService;

    private Meal meal;
    private MealRepository(LocalSource localSource, APIClient apiClientInterface) {
        this.localSource = localSource;
        this.apiClient = apiClientInterface;
        executorService = Executors.newSingleThreadExecutor();
    }

    public static synchronized MealRepository getInstance(LocalSource localSource, APIClient apiClient) {
        if (instance == null) {
            instance = new MealRepository(localSource, apiClient);
        }
        return instance;
    }


    @Override
    public void insertMealToFavourite(Meal meal) {
      executorService.execute(() ->  localSource.insertMeal(meal)) ;
    }

    @Override
    public void insertAllFav(List<Meal> meals) {

        executorService.execute(() ->  localSource.insertAllFav(meals)) ;

    }

    @Override
    public void deleteMealFromFavourite(Meal meal) {
        executorService.execute(() ->   localSource.deleteMeal(meal)) ;

    }

    @Override
    public void deleteAllFavouriteMeals() {
        executorService.execute(() ->    localSource.deleteAllMeals());
    }

    @Override
    public LiveData<Meal> getFavMealById(String id) {
        return localSource.getMealById(id);
    }

    @Override
    public LiveData<PlanedMeal> getPlanMealById(String id) {
        return localSource.getPlanMealById(id);
    }

    @Override
    public LiveData<List<Meal>> getAllFavouriteMeals() {
        return localSource.getAllMeals();
    }


    @Override
    public LiveData<List<PlanedMeal>> getAllPlannedMeals( ) {

        return localSource.getAllPlannedMeals();
    }

    @Override
    public LiveData<List<PlanedMeal>> getMealForDay(Date day) {

        return localSource.getMealForDay(day);
    }

    @Override
    public void insertMealToCalendar(PlanedMeal meal, Date day) {
        executorService.execute(() ->    localSource.insertMealToCalendar(meal, day));
    }



    @Override
    public void deletePlannedMeal(PlanedMeal meal) {
        executorService.execute(() ->    localSource.deletePlannedMeal(meal));
    }



    public void getMealByName(String name , NetworkCallBack call) {

        apiClient.getMealByName(name, call);

    }


    public void getMealByFirstChar(String firstChar , NetworkCallBack call) {
         apiClient.getMealByFirstChar(firstChar, call);
    }


    public void getMealById(String id , NetworkCallBack call) {
         apiClient.getMealById(id, call);
    }



    public void getRandomMeal(NetworkCallBack call) {
         apiClient.getMealRandom(call);
    }


    public void getAllCategories(NetworkCallBack call) {
         apiClient.getCategoriesList(call);
    }


    public void getAllCountries(NetworkCallBack call) {
         apiClient.getCountriesList(call);
    }


    public void getAllIngredients(NetworkCallBack call) {
         apiClient.getIngredientsList(call);
    }


    public void getMealsByIngredient(String ingredient , NetworkCallBack call) {
         apiClient.getMealByIngredient(ingredient,call);
    }


    public void getMealsByCategory(String category , NetworkCallBack call) {
         apiClient.getMealByCategory(category,call);
    }


    public void getMealsByCountry(String country , NetworkCallBack call) {
         apiClient.getMealByArea(country, call);
    }




}

