package com.example.luluchef.database;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.luluchef.model.Category;
import com.example.luluchef.model.CategoryResponse;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.IngredientResponse;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LocalSource implements LocalSourceInterface{

    private static final String TAG = "luluTrack";
    private static LocalSource instance = null;
    private MealDAO mealDao;

    private LocalSource(Context context){
        mealDao = MealDatabase.getInstance(context).getMealDAO();
    }

    public static synchronized LocalSource getInstance(Context context){
        if(instance == null){
            instance = new LocalSource(context);
        }
        return instance;
    }


    @Override
    public void insertMeal(Meal meal) {
        mealDao.insertMeal(meal);
    }

    @Override
    public void insertAllFav(List<Meal> meals) {
        mealDao.insertAllFav(meals);
    }

    @Override
    public void deleteMeal(Meal meal) {
        mealDao.deleteMeal(meal);
    }

    @Override
    public void deleteAllMeals() {
        mealDao.deleteAllMeals();
    }

    @Override
    public LiveData<List<Meal>> getAllMeals() {
        return mealDao.getAllMeals();
    }

    @Override
    public LiveData<List<PlanedMeal>> getAllPlannedMeals() {
        Log.i(TAG, "onChanged: lulu in local  ");
        return mealDao.getAllPlannedMeals();
    }

    @Override
    public LiveData<List<PlanedMeal>> getMealForDay(Date day) {
        Log.i(TAG, "onChanged: lulu in local  " + day);
        return  mealDao.getMealForDay(day);
    }

    @Override
    public void insertMealToCalendar(PlanedMeal meal , Date date) {
        meal.setDate(date);
        mealDao.insertPLannedMeal(meal);
    }

    @Override
    public Meal getMealById(String id) {
        return mealDao.getMealById(id);
    }

    @Override
    public void deletePlannedMeal(PlanedMeal meal) {
            mealDao.deletePlannedMeal(meal);
    }
}
