package com.example.luluchef.search.Filter.view;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.luluchef.R;
import com.example.luluchef.database.LocalSource;
import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Ingredient;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.APIClient;
import com.example.luluchef.search.Filter.presenter.FilterPresenter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class FilterFragment extends DialogFragment implements FilterView {


    private static FilterFragment instance = null;
    private final FragmentManager fragmentManager;
    private ChipGroup cgCategory, cgCountry, cgIngredient;
    private String category, country, ingredient;
    private final FilterOnClickListener onClick;
    private FilterPresenter presenter;
    private APIClient client;
    private LocalSource localSource;
    private MealRepository repo;

    public FilterFragment(FragmentManager fragmentManager, FilterOnClickListener onClick, String category, String country, String ingredient) {
        this.fragmentManager = fragmentManager;
        this.onClick = onClick;
        this.category = category;
        this.country = country;
        this.ingredient = ingredient;
    }

    public static FilterFragment getInstance(FragmentManager fragmentManager, FilterOnClickListener onClick, String category, String country, String ingredient)
    {
        if(instance == null )
        {
            instance= new FilterFragment(fragmentManager, onClick, category, country, ingredient);
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("Filter", "onSave");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cgCategory = view.findViewById(R.id.cg_category);
        cgCountry = view.findViewById(R.id.cg_country);
        cgIngredient = view.findViewById(R.id.cg_ingredient);
        Button btnContinue = view.findViewById(R.id.btn_continue);

        client = APIClient.getInstance();
        localSource = LocalSource.getInstance(view.getContext());
        repo = MealRepository.getInstance(localSource , client);
        presenter = new FilterPresenter(this , repo );

        presenter.getCountries();
        presenter.getCategories();
        presenter.getIngredients();

        cgCategory.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = group.findViewById(checkedId);
            if (chip != null) {
                category = chip.getText().toString().trim();
            } else {
                category = null;
            }
        });
        cgCountry.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = group.findViewById(checkedId);
            if (chip != null) {
                country = chip.getText().toString().trim();
            } else {
                country = null;
            }
        });
        cgIngredient.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = group.findViewById(checkedId);
            if (chip != null) {
                ingredient = chip.getText().toString().trim();
            } else {
                ingredient = null;
            }
        });

        btnContinue.setOnClickListener(v -> onClick.onFilterClick(category, country, ingredient));
    }


    private Chip createChip(String title) {
        Chip chip = (Chip) getLayoutInflater().inflate(R.layout.chip_layout, null);
        chip.setId(ViewCompat.generateViewId());
        chip.setText(title);
        return chip;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            if (getDialog().getWindow() != null) {
                getDialog().getWindow().setLayout(width, height);
            }
        }
    }

    @Override
    public void showMeals(List<Meal> meals) {

    }

    @Override
    public void showErr(String error) {

    }

    @Override
    public void showCountries(List<Country> countries) {
        for (Country countryObj : countries) {
            String countryName = countryObj.getStrArea();

            // Create a Chip for each category
            Chip chip = createChip(countryName);

            // Check if the category matches the current selection and set it as checked
            if (country != null && chip.getText().toString().trim().equals(country)) {
                chip.setChecked(true);
            }

            // Add the chip to the ChipGroup
            cgCountry.addView(chip);
        }
    }

    @Override
    public void showCategories(List<Category> categories) {
        for (Category categoryObj : categories) {
            String categoryName = categoryObj.getStrCategory();

            // Create a Chip for each category
            Chip chip = createChip(categoryName);

            // Check if the category matches the current selection and set it as checked
            if (category != null && chip.getText().toString().trim().equals(category)) {
                chip.setChecked(true);
            }

            // Add the chip to the ChipGroup
            cgCategory.addView(chip);
        }
    }

    @Override
    public void showIngrediants(List<Ingredient> ingredients) {
        for (Ingredient ingredientObj : ingredients) {
            String ingredientName = ingredientObj.getStrIngredient();

            // Create a Chip for each category
            Chip chip = createChip(ingredientName);

            // Check if the category matches the current selection and set it as checked
            if (ingredient != null && chip.getText().toString().trim().equals(ingredient)) {
                chip.setChecked(true);
            }

            // Add the chip to the ChipGroup
            cgIngredient.addView(chip);
        }
    }

    public void showFilter() {
        if (!instance.isVisible()) {
            instance.show(fragmentManager, "filter");
        }
    }

    public void dismissFilter() {
        instance.dismiss();
    }
}