package com.example.luluchef.planner.view;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import android.provider.CalendarContract;
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
    private String selectedMealType;
    // Holds selected meal type (Breakfast, Lunch, Dinner)



    private static final int REQUEST_CALENDAR_PERMISSION = 100;
    private void checkCalendarPermission() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR},
                    REQUEST_CALENDAR_PERMISSION);
        }
    }

    public DayFragment(Meal meal) {
        this.meal = meal;
    }

    public  DayFragment(){

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

        // Check for calendar permissions
        checkCalendarPermission();




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

          //  Toast.makeText(getContext(), "Meal saved for " + selectedMealType + " on " + selectedDate, Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Meal saved: " + selectedMealType + " on " + selectedDate);

            // Add meal to calendar
            addMealToCalendar(selectedMealType,meal.getStrMeal() ,selectedDate);

            // Optionally close the fragment after saving
            dismiss();
        }
    }



    @Override
    public void showErr(String error) {
        // Implement error handling
    }

    @Override
    public void showDatemeal(List<PlanedMeal> meals) {
        // Implement date-specific meal display logic
    }

    private void addMealToCalendar(String mealType, String mealName, Date date) {
        // Get the calendar's ID (you can also allow users to select which calendar to use)
        long calendarId = 1; // Replace with the desired calendar ID or fetch programmatically

        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.TITLE, mealType + " Meal"); // Event title
        values.put(CalendarContract.Events.DESCRIPTION, "Planned to eat " + mealName); // Event description
        values.put(CalendarContract.Events.CALENDAR_ID, calendarId); // Calendar ID
        values.put(CalendarContract.Events.DTSTART, date.getTime()); // Start time
        values.put(CalendarContract.Events.DTEND, date.getTime() + 60 * 60 * 1000); // End time (1 hour later)
        values.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().getTimeZone().getID()); // Timezone

        // Insert event into the calendar
        Uri uri = getContext().getContentResolver().insert(CalendarContract.Events.CONTENT_URI, values);
        if (uri != null) {
            Toast.makeText(getContext(), "Meal added to calendar!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Failed to add meal to calendar.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALENDAR_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // Permissions granted, you can now add meals to the calendar
            } else {
                Toast.makeText(getContext(), "Calendar permissions are required to add meals.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
