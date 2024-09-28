package com.example.luluchef.planner.Presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.planner.view.PlanView;

import java.util.Date;
import java.util.List;

public class PlanPresenter implements PlanPresenterInterface{

    private PlanView view;
    private MealRepository repo;
    LiveData<List<PlanedMeal>>  planMealList;
    public PlanPresenter(PlanView view, MealRepository repo) {
        this.view = view;
        this.repo = repo;
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
    public void getAllPlannedMeal() {
       planMealList = repo.getAllPlannedMeals();
        planMealList.observeForever(new Observer<List<PlanedMeal>>() {
            @Override
            public void onChanged(List<PlanedMeal> meals) {
                view.showMeals(meals);
            }
        });

    }
}
