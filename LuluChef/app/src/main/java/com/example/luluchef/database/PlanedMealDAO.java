package com.example.luluchef.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.luluchef.model.PlanedMeal;

import java.util.List;

@Dao
public interface PlanedMealDAO {

    @Query("SELECT * FROM planMeal_table")
    LiveData<List<PlanedMeal>> getPlanedMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PlanedMeal mealPlan);

    @Delete
    void deleteMealPlan(PlanedMeal mealPlan);
}
