package com.example.luluchef.planner.Presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.planner.view.PlanFragment;
import com.example.luluchef.planner.view.PlanView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PlanPresenter implements PlanPresenterInterface{



    private static final String TAG = "lol";
    private PlanView view;
    private MealRepository repo;
    LiveData<List<PlanedMeal>>  planMealList;
    LifecycleOwner lifecycleOwner;
    public PlanPresenter(PlanView view, MealRepository repo , LifecycleOwner lifecycleOwner) {
        this.view = view;
        this.repo = repo;
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public void AddtoPlannedTable(PlanedMeal meal , Date date) {
        repo.insertMealToCalendar(meal , date);
    }

    @Override
    public void removeFromPlannedTable(PlanedMeal meal){
        repo.deletePlannedMeal(meal);
    }


    @Override
    public void getPlannedMealByDate(Date selectedDate)
    {
        planMealList = repo.getMealForDay(selectedDate);
        Log.i(TAG, "getPlannedMealByDate: "+selectedDate);
        // Observe changes in the meal list
        planMealList.observeForever(new Observer<List<PlanedMeal>>() {
            @Override
            public void onChanged(List<PlanedMeal> meals) {
              //  Log.i(TAG, "onChanged: ");
                if (meals != null && !meals.isEmpty()) {
                    // Pass the filtered meals to the view
                    Log.i(TAG, "onChanged: " + meals.get(0).getMeal().getStrMeal());
                    view.showDatemeal(meals);
                }
                else {
                    Log.i(TAG, "onChanged: errorrrrrrrrr  ");
                    view.showErr("no result");
                }
            }
        });

      /*  planMealList.observe(lifecycleOwner, new Observer<List<PlanedMeal>>() {
            @Override
            public void onChanged(List<PlanedMeal> meals) {
                if (meals != null && !meals.isEmpty()) {
                    // Pass the filtered meals to the view
                    Log.i(TAG, "onChanged: " + meals.get(0).getMeal().getStrMeal());
                    view.showDatemeal(meals);
                }
                else {
                    Log.i(TAG, "onChanged: errorrrrrrrrr  ");
                    view.showErr("no result");
                }
            }
        });*/
    }

    /* @Override
    public void getAllPlannedMeal(Date selectedDate) {
        // Retrieve all planned meals
        planMealList = repo.getAllPlannedMeals();

        // Observe changes in the meal list
        planMealList.observeForever(new Observer<List<PlanedMeal>>() {
            @Override
            public void onChanged(List<PlanedMeal> meals) {
                if (meals != null && !meals.isEmpty()) {
                    // Filter meals by a specific date (if required)
                    List<PlanedMeal> filteredMeals = filterMealsByDate(meals, selectedDate); // Replace new Date() with your specific date

                    // Pass the filtered meals to the view
                    view.showDatemeal(filteredMeals);
                }
            }
        });
    }

    // Helper function to filter meals by a specific date
    private List<PlanedMeal> filterMealsByDate(List<PlanedMeal> meals, Date targetDate) {
        List<PlanedMeal> filteredList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedTargetDate = sdf.format(targetDate); // Format target date

        for (PlanedMeal meal : meals) {
            String mealDate = sdf.format(meal.getDate()); // Format each meal's date
            if (formattedTargetDate.equals(mealDate)) {
                filteredList.add(meal); // Add meals that match the target date
            }
        }
        return filteredList;
    }*/


}
