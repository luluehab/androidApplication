package com.example.luluchef.details.presenter;

import com.example.luluchef.details.view.DetailsView;
import com.example.luluchef.model.Meal;
import com.example.luluchef.network.APIClient;
import com.example.luluchef.network.NetworkCallBack;

import java.util.List;

public class DetailPresenter implements DetailPresenterInterface , NetworkCallBack<Meal> {
    private final DetailsView view;
    private final APIClient client;
    public DetailPresenter(DetailsView view , APIClient client) {
        this.view = view;
        this.client = client;
    }


    @Override
    public void loadMealsInDetails(String id) {

       client.getMealById(id , this);
    }



    @Override
    public void addToFavourite(Meal meal) {

    }

    @Override
    public void onSuccess(List<Meal> response) {
        view.showDetails(response.get(0));
    }

    @Override
    public void onFailure(String error) {
        view.showErr(error);
    }
}
