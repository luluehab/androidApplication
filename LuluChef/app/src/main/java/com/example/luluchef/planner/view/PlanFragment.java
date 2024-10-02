package com.example.luluchef.planner.view;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.luluchef.R;
import com.example.luluchef.database.LocalSource;
import com.example.luluchef.favourite.Presenter.FavPresenter;
import com.example.luluchef.model.PlanedMeal;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.APIClient;
import com.example.luluchef.planner.Presenter.PlanPresenter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class PlanFragment extends Fragment implements PlanView , onPlanClickListener{


    private RecyclerView plannedRecycler;
    private RecyclerView.LayoutManager planLayoutManager;
    private PlanAdapter planAdapter;
    private CalendarView calendarView;
    private APIClient client;
    private LocalSource localSource;
    private PlanPresenter presenter;
    private MealRepository repo;
    private List<PlanedMeal> plannedMeals;
    private Date selectedDate;

    public PlanFragment() {

    }

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

        plannedRecycler = view.findViewById(R.id.planrecycler);
        planLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL , false);
        plannedRecycler.setLayoutManager(planLayoutManager);
        calendarView = view.findViewById(R.id.Plancalendar);
        plannedRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        client = APIClient.getInstance();
        localSource = LocalSource.getInstance(view.getContext());
        repo = MealRepository.getInstance(localSource , client);
        presenter = new PlanPresenter (this , repo );

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            selectedDate = calendar.getTime();
            //presenter.getMealForDay(selectedDate);
            showMealWithDate();

        });

    }

    private void showMealWithDate() {
        if (selectedDate != null) {
            presenter.getAllPlannedMeal(selectedDate);
        }

    }


    @Override
    public void showMeals(List<PlanedMeal> meals) {

    }

    @Override
    public void showErr(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDatemeal(List<PlanedMeal> meals) {

        /*if (planAdapter != null)
        {
            planAdapter.clearMeals();
        }*/

        if(planAdapter == null)
        {
            planAdapter = new PlanAdapter(meals,getContext(),this);
            plannedRecycler.setAdapter(planAdapter);
        }
        else
        {
            planAdapter.updateMeals(meals);
        }


    }

    @Override
    public void onDelClicked(PlanedMeal meal) {
        presenter.removeFromPlannedTable(meal);
    }

    @Override
    public void onMealItemClicked(String id) {
        Log.i(TAG, "onMealItemClicked: " + id);
        Bundle args = new Bundle();
        args.putString("id", id);
        args.putString("from", "Plan");
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.action_HOmeFrag_to_detailFrag, args);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}