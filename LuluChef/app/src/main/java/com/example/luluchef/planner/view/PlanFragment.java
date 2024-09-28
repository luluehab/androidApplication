package com.example.luluchef.planner.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.luluchef.R;
import com.example.luluchef.database.LocalSource;
import com.example.luluchef.model.PlanedMeal;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.APIClient;
import com.example.luluchef.planner.Presenter.PlanPresenter;

import java.util.List;


public class PlanFragment extends Fragment implements PlanView , onPlanClickListener{


    private RecyclerView plannedRecycler;
    private PlanAdapter planAdapter;
    private PlanPresenter presenter;
    private MealRepository repo;
    private List<PlanedMeal> plannedMeals;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_plan, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        plannedRecycler = view.findViewById(R.id.plannedMealsRecyclerView);
        plannedRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        repo = MealRepository.getInstance(LocalSource.getInstance(getContext()), APIClient.getInstance());
        presenter = new PlanPresenter(this, repo);

        presenter.getAllPlannedMeal(); // Fetch meals from the database

    }

    @Override
    public void showMeals(List<PlanedMeal> meals) {
        planAdapter = new PlanAdapter(meals,getContext(),this);
        plannedRecycler.setAdapter(planAdapter);
    }

    @Override
    public void showErr(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDelClicked(PlanedMeal meal) {
        presenter.removeFromPlannedTable(meal);
    }
}