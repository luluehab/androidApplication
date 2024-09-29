package com.example.luluchef.network;

import com.example.luluchef.model.Category;
import com.example.luluchef.model.CategoryResponse;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.Meal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class APIClient implements APIClientInterface {

    private static final String baseURL =  "https://www.themealdb.com/api/json/v1/1/";
    private final APIService apiService;
    private static APIClient client;


    public APIClient(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);
    }
    public static synchronized APIClient getInstance(){
        if(client == null )
        {
            client=new APIClient();
        }
        return client;
    }


    @Override
    public void getMealByName(String name, NetworkCallBack callback) {
        Call<NetworkResponse<Meal>> call = apiService.getMealByName(name);
        call.enqueue(new Callback<NetworkResponse<Meal>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Meal>> call, Response<NetworkResponse<Meal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccessResultMeal(response.body().meals);
                } else {
                    callback.onFailure("Failed to get meal by name");
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<Meal>> call, Throwable throwable) {
                callback.onFailure(throwable.getMessage());
            }
        });

    }

    @Override
    public void getCategoriesList(NetworkCallBack callback){
        Call<CategoryResponse> call = apiService.getCategoriesList();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccessResultCategory(response.body().getCategories());
                } else {
                    callback.onFailure("Failed to get categories");
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {
                callback.onFailure(throwable.getMessage());
            }
        });
    }
    @Override
    public void getMealByCategory(String category, NetworkCallBack callback) {
        Call<NetworkResponse<Meal>> call = apiService.getMealByCategory(category);
        call.enqueue(new Callback<NetworkResponse<Meal>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Meal>> call,retrofit2.Response<NetworkResponse<Meal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccessResultMeal(response.body().meals);
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

    @Override
    public void getCountriesList( NetworkCallBack callback) {
        Call<NetworkResponse<Country>> call = apiService.getCountriesList();
        call.enqueue(new Callback<NetworkResponse<Country>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Country>> call,retrofit2.Response<NetworkResponse<Country>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccessResultCountries(response.body().meals);
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

    @Override
    public void getMealByArea(String area, NetworkCallBack callback) {
        Call<NetworkResponse<Meal>> call = apiService.getMealByArea(area);
        call.enqueue(new Callback<NetworkResponse<Meal>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Meal>> call,retrofit2.Response<NetworkResponse<Meal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccessResultMeal(response.body().meals);
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

    @Override
    public void getIngredientsList(NetworkCallBack callback) {
        Call<NetworkResponse<Ingredient>> call = apiService.getIngredientsList();
        call.enqueue(new Callback<NetworkResponse<Ingredient>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Ingredient>> call,retrofit2.Response<NetworkResponse<Ingredient>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccessResultIngredient(response.body().meals);
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

    @Override
    public void getMealByIngredient(String ingredient, NetworkCallBack callback) {
        Call<NetworkResponse<Meal>> call = apiService.getMealByIngredient(ingredient);
        call.enqueue(new Callback<NetworkResponse<Meal>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Meal>> call,retrofit2.Response<NetworkResponse<Meal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccessResultMeal(response.body().meals);
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

    @Override
    public void getMealById(String id , NetworkCallBack callback)
    {
        Call<NetworkResponse<Meal>> call = apiService.getMealById(id);
        call.enqueue(new Callback<NetworkResponse<Meal>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Meal>> call,retrofit2.Response<NetworkResponse<Meal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccessResultMeal(response.body().meals);
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
    @Override
    public void getMealRandom(NetworkCallBack callback) {

        /*

        // to get only one
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
        });*/

        final List<Meal> randomMeals = new ArrayList<>();
        final int totalRandomMeals = 5;  // Number of random meals to fetch

        for (int i = 0; i < totalRandomMeals; i++) {
            Call<NetworkResponse<Meal>> call = apiService.getMealRandom();
            call.enqueue(new Callback<NetworkResponse<Meal>>() {
                @Override
                public void onResponse(Call<NetworkResponse<Meal>> call, retrofit2.Response<NetworkResponse<Meal>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        randomMeals.addAll(response.body().meals);

                        // Check if we've received all 5 random meals
                        if (randomMeals.size() == totalRandomMeals) {
                            callback.onSuccessResultMeal(randomMeals);  // Return the list once we have all 5
                        }
                    } else {
                        callback.onFailure("Failed to get random meals");
                    }
                }

                @Override
                public void onFailure(Call<NetworkResponse<Meal>> call, Throwable throwable) {
                    callback.onFailure(throwable.getMessage());
                }
            });
        }
    }

    @Override
    public void getMealByFirstChar(String firstChar, NetworkCallBack callBack) {
        Call<NetworkResponse<Meal>> call = apiService.getMealByFirstChar(firstChar);
        call.enqueue(new Callback<NetworkResponse<Meal>>() {
            @Override
            public void onResponse(Call<NetworkResponse<Meal>> call, Response<NetworkResponse<Meal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onSuccessResultMeal(response.body().meals);  // Return the list once we have all 5
                } else {
                    callBack.onFailure("Failed to get meal by first Char");
                }
            }

            @Override
            public void onFailure(Call<NetworkResponse<Meal>> call, Throwable throwable) {
                callBack.onFailure(throwable.getMessage());
            }
        });
    }


}
