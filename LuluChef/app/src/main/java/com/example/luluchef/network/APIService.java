package com.example.luluchef.network;

import androidx.lifecycle.LiveData;

import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    // for Category
    @GET("list.php?c=list")
    Call<NetworkResponse<Category>> getCategoriesList();
    @GET("filter.php")
    Call<NetworkResponse<Meal>> getMealByCategory(@Query("c") String category);


    //for Country
    @GET("list.php?a=list")
    Call<NetworkResponse<Country>> getCountriesList();
    @GET("filter.php")
    Call<NetworkResponse<Meal>> getMealByArea(@Query("a") String area);


    //for Ingredients
    @GET("list.php?i=list")
    Call<NetworkResponse<Ingredient>> getIngredientsList();
    @GET("filter.php")
    Call<NetworkResponse<Meal>> getMealByIngredient(@Query("i") String ingredient);


    // by Id
    @GET("lookup.php")
    Call<NetworkResponse<Meal>> getMealById(@Query("i") String id);


    // for random
    @GET("random.php")
    Call<NetworkResponse<Meal>> getMealRandom();


    //for search
    @GET("search.php")
    Call<NetworkResponse<Meal>> getMealByName(@Query("s")String name);

    @GET("search.php")
    Call<NetworkResponse<Meal>> getMealByFirstChar(@Query("f") String firstChar);

    @GET("filter.php")//this return list of strMeal and strMealThumb and idMeal just
    Call<NetworkResponse<Meal>> getMealsByIngredient(@Query("i") String ingredient);

    @GET("filter.php")//this return list of strMeal and strMealThumb and idMeal just
    Call<NetworkResponse<Meal>> getMealsByCategory(@Query("c") String category);

    @GET("filter.php")//this return list of strMeal and strMealThumb and idMeal just
    Call<NetworkResponse<Meal>> getMealsByCountry(@Query("a") String country);

}
