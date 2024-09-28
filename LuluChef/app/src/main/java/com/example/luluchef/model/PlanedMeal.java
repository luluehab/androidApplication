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
    @ColumnInfo(name = "planned_date")
    private Date date;

    // Constructors, getters, and setters
    public PlanedMeal(String idMeal, Date date) {
        this.idMeal = idMeal;
        this.date = date;
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
