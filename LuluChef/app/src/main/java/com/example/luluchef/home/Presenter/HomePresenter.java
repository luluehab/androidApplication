package com.example.luluchef.home.Presenter;

import android.widget.Toast;

import com.example.luluchef.database.MealRepository;
import com.example.luluchef.home.view.HomeView;
import com.example.luluchef.model.Meal;
import com.example.luluchef.network.APIClient;
import com.example.luluchef.network.NetworkCallBack;

import java.util.List;

public class HomePresenter implements HomePresenterInterface, NetworkCallBack<Meal> {

    private final HomeView view;
   // private final MealRepository mealRepo;
    private final APIClient client;
    public HomePresenter(HomeView view, APIClient client) {
        this.view = view;
        this.client = client;
    }

    @Override
    public void loadMeals() {
        //APIClient client1 = APIClient.getInstance();
        client.getMealRandom(this);

    }

    @Override
    public void addToFavourite(Meal meal) {

    }

    @Override
    public void onSuccess(List<Meal> response) {
        view.showMeals(response);
    }

    @Override
    public void onFailure(String error) {
        view.showErr(error);
    }
}
