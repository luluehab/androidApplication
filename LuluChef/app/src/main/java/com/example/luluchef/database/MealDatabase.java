package com.example.luluchef.database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;

@Database(entities = {Meal.class, PlanedMeal.class},version = 8)
@TypeConverters({Converters.class})  // Add this line
public abstract class MealDatabase extends RoomDatabase {
    private static MealDatabase instance = null;
    public abstract MealDAO getMealDAO();
    public static synchronized MealDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MealDatabase.class, "mealDb")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //.fallbackToDestructiveMigrationFrom(5, 6)
        return instance;
    }
}
