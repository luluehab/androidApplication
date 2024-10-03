package com.example.luluchef.planner.Presenter;

import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;

import java.util.Date;

public interface PlanPresenterInterface {

    void AddtoPlannedTable(PlanedMeal meal , Date date) ;
    void removeFromPlannedTable(PlanedMeal meal);
    void getPlannedMealByDate(Date selectedDate);
    //void getAllPlannedMeal(Date selectedDate);

}
