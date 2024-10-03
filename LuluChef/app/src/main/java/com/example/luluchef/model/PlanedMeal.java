package com.example.luluchef.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "planMeal_table")
public class PlanedMeal {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String idMeal;

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    @ColumnInfo(name = "date")
    private Date date;
    private String mealType;
    private Meal meal;

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }


    public String getMealType() {
        return mealType;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Meal getMeal() {
        return meal;
    }

    public int getId() {
        return id;
    }


    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setDate(Date date) {
        this.date = date;
    }

   public PlanedMeal() {
   }

    public PlanedMeal(Meal meal, Date day ,String idMeal ,  String mealType)
    {
        this.meal = meal;
        this.date = day;
        this.mealType = mealType;
        this.idMeal = idMeal;

    }

}

