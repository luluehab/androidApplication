package com.example.luluchef.network;

import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.Meal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface APIClientInterface {


    void getMealByName(String name , NetworkCallBack callback);
    void getCategoriesList(NetworkCallBack callback);
    void getMealByCategory(String category, NetworkCallBack callback);
    void getCountriesList( NetworkCallBack callback) ;
    void getMealByArea(String area, NetworkCallBack callback) ;
    void getIngredientsList(NetworkCallBack callback);
    void getMealByIngredient(String ingredient, NetworkCallBack callback) ;
    void getMealById(String id , NetworkCallBack callback);
    void getMealRandom(NetworkCallBack callback) ;
    void getMealByFirstChar(String firstChar, NetworkCallBack callBack);

}
