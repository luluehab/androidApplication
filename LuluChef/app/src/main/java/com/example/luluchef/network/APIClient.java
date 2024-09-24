package com.example.luluchef.network;

import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.Meal;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class APIClient {

    private static final String baseURL =  "https://www.themealdb.com/api/json/v1/1/";
    private final APIService apiService;
    private static APIClient client;


    private APIClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);
    }
    public static APIClient getInstance(){
        if(client == null )
        {
            client=new APIClient();
        }
        return client;
    }

    public void getCategoriesList(NetworkCallBack<Category> callback){
        Call<NetworkResponse<Category>> call = apiService.getCategoriesList();
        call.enqueue(new Callback<NetworkResponse<Category>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Category>> call, Response<NetworkResponse<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().meals);
                } else {
                    callback.onFailure("Failed to get categories");
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<Category>> call, Throwable throwable) {
                callback.onFailure(throwable.getMessage());
            }
        });
    }

    public void getMealByCategory(String category, NetworkCallBack<Meal> callback) {
        Call<NetworkResponse<Meal>> call = apiService.getMealByCategory(category);
        call.enqueue(new Callback<NetworkResponse<Meal>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Meal>> call,retrofit2.Response<NetworkResponse<Meal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().meals);
                } else {
                    callback.onFailure("Failed to get meals by category");
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<Meal>> call,Throwable throwable) {
                callback.onFailure(throwable.getMessage());
            }
        });
    }

    public void getCountriesList( NetworkCallBack<Country> callback) {
        Call<NetworkResponse<Country>> call = apiService.getCountriesList();
        call.enqueue(new Callback<NetworkResponse<Country>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Country>> call,retrofit2.Response<NetworkResponse<Country>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().meals);
                } else {
                    callback.onFailure("Failed to get meals by Country");
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<Country>> call, Throwable throwable) {
                callback.onFailure(throwable.getMessage());
            }
        });
    }

    public void getMealByArea(String area, NetworkCallBack<Meal> callback) {
        Call<NetworkResponse<Meal>> call = apiService.getMealByArea(area);
        call.enqueue(new Callback<NetworkResponse<Meal>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Meal>> call,retrofit2.Response<NetworkResponse<Meal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().meals);
                } else {
                    callback.onFailure("Failed to get meals by Country");
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<Meal>> call, Throwable throwable) {
                callback.onFailure(throwable.getMessage());
            }
        });
    }

    public void getIngredientsList(NetworkCallBack<Ingredient> callback) {
        Call<NetworkResponse<Ingredient>> call = apiService.getIngredientsList();
        call.enqueue(new Callback<NetworkResponse<Ingredient>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Ingredient>> call,retrofit2.Response<NetworkResponse<Ingredient>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().meals);
                } else {
                    callback.onFailure("Failed to get meals by Country");
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<Ingredient>> call, Throwable throwable) {
                callback.onFailure(throwable.getMessage());
            }
        });
    }

    public void getMealByIngredient(String ingredient, NetworkCallBack<Meal> callback) {
        Call<NetworkResponse<Meal>> call = apiService.getMealByIngredient(ingredient);
        call.enqueue(new Callback<NetworkResponse<Meal>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Meal>> call,retrofit2.Response<NetworkResponse<Meal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().meals);
                } else {
                    callback.onFailure("Failed to get meals by Country");
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<Meal>> call, Throwable throwable) {
                callback.onFailure(throwable.getMessage());
            }
        });
    }

    public void getMealRandom(NetworkCallBack<Meal> callback) {
        Call<NetworkResponse<Meal>> call = apiService.getMealRandom();
        call.enqueue(new Callback<NetworkResponse<Meal>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Meal>> call,retrofit2.Response<NetworkResponse<Meal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().meals);
                } else {
                    callback.onFailure("Failed to get meals by Country");
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<Meal>> call, Throwable throwable) {
                callback.onFailure(throwable.getMessage());
            }
        });
    }

}
