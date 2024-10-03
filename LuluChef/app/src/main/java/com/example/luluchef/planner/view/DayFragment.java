package com.example.luluchef.planner.view;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
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
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DayFragment extends DialogFragment implements PlanView {

    private CalendarView calendarView;
    private ChipGroup mealChipGroup;
    private Button submitMealButton;
    private Date selectedDate;
    private PlanPresenter presenter;
    private MealRepository repo;
    private LocalSource localSource;
    private APIClient client;
    private Meal meal;

    private String selectedMealType; // Holds selected meal type (Breakfast, Lunch, Dinner)

    public DayFragment(Meal meal) {
        this.meal = meal;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);

        // Initialize views
        calendarView = view.findViewById(R.id.calendarView);
        mealChipGroup = view.findViewById(R.id.mealChipGroup);
        submitMealButton = view.findViewById(R.id.submitMealButton);

        // Set the minimum selectable date to today's date
        calendarView.setMinDate(System.currentTimeMillis());

        // Initialize dependencies
        client = APIClient.getInstance();
        localSource = LocalSource.getInstance(view.getContext());
        repo = MealRepository.getInstance(localSource, client);
        presenter = new PlanPresenter(this, repo, getViewLifecycleOwner());

        // Set a listener for the calendar view to capture the selected date
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            selectedDate = calendar.getTime();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            selectedDate = calendar.getTime();
            Log.i(TAG, "onViewCreated: Selected date " + selectedDate);
        });

        // Handle the meal selection logic
        mealChipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            Chip selectedChip = view.findViewById(checkedId);
            if (selectedChip != null) {
                    selectedMealType = selectedChip.getText().toString(); // Get selected meal type
                Log.i(TAG, "Meal selected: " + selectedMealType);
            }

        });

        // Handle submit button click
        submitMealButton.setOnClickListener(v -> {
            if (selectedDate != null && selectedMealType != null && meal.getIdMeal() != null) {
                saveMealWithDate(); // Save the meal with the selected date and type
            } else {
                Toast.makeText(getContext(), "Please select a date and a meal type.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void saveMealWithDate() {
        if (selectedDate != null && meal.getIdMeal() != null && selectedMealType != null) {
            PlanedMeal plannedMeal = new PlanedMeal(meal, selectedDate, meal.getIdMeal(), selectedMealType);
            presenter.AddtoPlannedTable(plannedMeal, selectedDate); // Save the planned meal in the database

            Toast.makeText(getContext(), "Meal saved for " + selectedMealType + " on " + selectedDate, Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Meal saved: " + selectedMealType + " on " + selectedDate);

            // Optionally close the fragment after saving
            dismiss();
        }
    }

    @Override
    public void showMeals(List<PlanedMeal> meals) {
        // Implement display logic
    }

    @Override
    public void showErr(String error) {
        // Implement error handling
    }

    @Override
    public void showDatemeal(List<PlanedMeal> meals) {
        // Implement date-specific meal display logic
    }
}
