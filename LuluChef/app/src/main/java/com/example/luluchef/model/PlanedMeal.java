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
    private Meal meal;

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
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

    public PlanedMeal(Meal meal, Date day ,String idMeal)
    {
        this.meal = meal;
        this.date = day;
        this.idMeal = idMeal;

    }

}

