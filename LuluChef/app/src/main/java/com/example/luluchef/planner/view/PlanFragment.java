package com.example.luluchef.planner.view;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

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
        presenter = new PlanPresenter (this , repo , getViewLifecycleOwner() );
        Log.i(TAG, "onViewCreated: ");
       //                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("selectedDatePrefs", Context.MODE_PRIVATE);
       //                long dateMillis = sharedPreferences.getLong("selectedDate", -1); // Default value -1 if not found
       //                if (dateMillis != -1) {
       //                    selectedDate = new Date(dateMillis); // Restore date from long
       //                    calendarView.setDate(selectedDate.getTime(), true, true); // Set the date on CalendarView
       //                    Log.i(TAG, "onViewCreated: in shared done "+ selectedDate);
       //                }
       //                else{
       //                    //long todayMillis = calendarView.getDate();
       //                    //selectedDate = new Date(todayMillis);
       //                    selectedDate = todayDate;
       //                    calendarView.setDate(selectedDate.getTime(), true, true); // Set the date on CalendarView
       //                    Log.i(TAG, "onViewCreated: sorrrrrrrryyyyyy " + selectedDate);
       //                }

        // Get today's date from the CalendarView
      //  long todayMillis = calendarView.getDate();


        selectedDate = new Date();
        // Create a Calendar instance and set the time to the selected date
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(selectedDate);
        // Set the time components to zero
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        // Update the selectedDate to the modified date
        selectedDate = calendar1.getTime();
        showMealWithDate(selectedDate);

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date selectedDate = calendar.getTime();
            //Log.i(TAG, "onViewCreated: on Saviiiiiiiing " + selectedDate);
            //presenter.getMealForDay(selectedDate);
            showMealWithDate(selectedDate);

        });


    }


    private void showMealWithDate(Date date) {
        if (date != null) {
            Log.i(TAG, "showMealWithDate: "+ date);
            presenter.getPlannedMealByDate(date);
        }

    }


    @Override
    public void showErr(String error) {
        if(planAdapter != null) {
            Log.i(TAG, "showErr: "+ error);
           planAdapter.clearMeals();
        }
        //Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    // for adapter
    @Override
    public void showDatemeal(List<PlanedMeal> meals) {

         if(planAdapter != null)
        {
            Log.i(TAG, "showDatemeal: plan adapter is not null ");
            //planAdapter = new PlanAdapter(new ArrayList<>(),getContext(),this);
            //plannedRecycler.setAdapter(planAdapter);
            //planAdapter.notifyDataSetChanged();
            planAdapter.clearMeals();
        }

           //  Log.i(TAG, "showDatemeal: " + meals.get(0).getMeal().getStrMeal());
             planAdapter = new PlanAdapter(meals,getContext(),this);
             plannedRecycler.setAdapter(planAdapter);

    }

    @Override
    public void onDelClicked(PlanedMeal meal) {
        Log.i(TAG, "onDelClicked: " + meal.getDate());
        presenter.removeFromPlannedTable(meal);
        presenter.getPlannedMealByDate(meal.getDate());


    }

    @Override
    public void onMealItemClicked(String id) {
        Log.i(TAG, "onMealItemClicked: " + id);
        Bundle args = new Bundle();
        args.putString("id", id);
        args.putString("from", "Plan");
        NavController navController = Navigation.findNavController(getView());
       // isComingFromDetails = true;
        navController.navigate(R.id.action_HOmeFrag_to_detailFrag, args);
    }

}