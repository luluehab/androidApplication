package com.example.luluchef.planner.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.luluchef.R;
import com.example.luluchef.database.LocalSource;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.APIClient;
import com.example.luluchef.planner.Presenter.PlanPresenter;

import java.security.PrivateKey;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DayFragment extends DialogFragment implements PlanView {

    CalendarView calendarView ;
    private Date selectedDate;
    private PlanPresenter presenter;
    private MealRepository repo;
    private LocalSource localSource;
    private APIClient client;
    private Meal meal;

    public DayFragment(Meal meal) {

        this.meal = meal;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        // Set the minimum selectable date to today's date
        calendarView.setMinDate(System.currentTimeMillis());

        client = APIClient.getInstance();
        localSource = LocalSource.getInstance(view.getContext());
        repo = MealRepository.getInstance(localSource , client);
        presenter = new PlanPresenter(this , repo);
        // Set a listener for the calendar view to capture the selected date

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            selectedDate = calendar.getTime();
            saveMealWithDate();  // Save the meal with the selected date

        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void saveMealWithDate() {
        if (selectedDate != null && meal.getIdMeal() != null) {
            PlanedMeal plannedMeal = new PlanedMeal(meal.getIdMeal(), selectedDate , meal.getStrMeal(),meal.getStrCategory() , meal.getStrArea() , meal.getStrMealThumb());
            presenter.AddtoPlannedTable(plannedMeal, selectedDate); // Save the planned meal in the database
            Toast.makeText(getContext(), "Meal saved for " + selectedDate, Toast.LENGTH_SHORT).show();
            // Optionally close the fragment after saving
                dismiss();
        }
    }

    @Override
    public void showMeals(List<PlanedMeal> meals) {

    }

    @Override
    public void showErr(String error) {

    }

    @Override
    public void showDatemeal(List<PlanedMeal> meals) {

    }
}