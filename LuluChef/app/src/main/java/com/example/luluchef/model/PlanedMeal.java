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
    @ColumnInfo(name = "date")
    private Date date;

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    //private String strMeal;
    //private String strCategory;
    //private String strArea;
   // private String strInstructions;
   // private String strMealThumb;
    //private String strTags;
    //private String strYoutube;
   private Meal meal;

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

    // Constructors, getters, and setters

   /* public PlanedMeal(String idMeal, Date date, String strMeal, String strCategory, String strArea, String strMealThumb) {
        this.idMeal = idMeal;
        this.date = date;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strMealThumb = strMealThumb;
    }*/
   public PlanedMeal() {
   }

    public PlanedMeal(Meal meal, Date day ,String idMeal)
    {
        this.meal = meal;
        this.date = day;
        this.idMeal = idMeal;

    }

}

