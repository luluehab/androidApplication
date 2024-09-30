package com.example.luluchef.database;

import androidx.room.TypeConverter;

import com.example.luluchef.model.Meal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;

public class Converters {
    @TypeConverter
    public static Long fromDate(Date date) {
        return date != null ? date.getTime() : null;
    }

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp != null ? new Date(timestamp) : null;
    }

    @TypeConverter
    public String fromMeal(Meal meal) {
        if (meal == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(meal);
    }

    @TypeConverter
    public Meal toMeal(String mealString) {
        if (mealString == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Meal>() {}.getType();
        return gson.fromJson(mealString, type);
    }
}
