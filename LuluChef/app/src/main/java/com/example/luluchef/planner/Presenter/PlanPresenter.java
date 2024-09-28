package com.example.luluchef.planner.Presenter;

import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.planner.view.PlanView;

import java.util.Date;

public class PlanPresenter implements PlanPresenterInterface{

    private PlanView view;
    private MealRepository repo;

    public PlanPresenter(PlanView view, MealRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void AddtoPlannedTable(PlanedMeal meal , Date date) {
        repo.insertMealToCalendar(meal , date);
    }

    @Override
    public void addToFavourite(Meal meal) {

    }
}
