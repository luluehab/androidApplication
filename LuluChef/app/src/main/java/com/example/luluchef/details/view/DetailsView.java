package com.example.luluchef.details.view;

import com.example.luluchef.model.Meal;

import java.util.List;

public interface DetailsView {

    void showDetails(Meal meal);
    void showErr(String error);

}
