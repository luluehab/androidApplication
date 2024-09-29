package com.example.luluchef.home.view;

import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Meal;

public interface HomeOnClickListener {
    void onMealItemClicked(String id);
    void onFavClicked(Meal meal);
    void onCalClicked(Meal meal);

    void onCategoryItemClicked(Category category);
    void onCountryItemClicked(Country country);
}
