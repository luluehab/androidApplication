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
    private String strMeal;
    private String strCategory;
    private String strArea;
    private String strMealThumb;

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    // Constructors, getters, and setters

    public PlanedMeal(String idMeal, Date date, String strMeal, String strCategory, String strArea, String strMealThumb) {
        this.idMeal = idMeal;
        this.date = date;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strMealThumb = strMealThumb;
    }

    public int getId() {
        return id;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
