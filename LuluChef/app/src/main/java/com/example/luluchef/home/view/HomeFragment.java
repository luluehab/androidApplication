package com.example.luluchef.home.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.luluchef.R;
import com.example.luluchef.database.LocalSource;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.home.Presenter.HomePresenter;
import com.example.luluchef.model.Meal;
import com.example.luluchef.network.APIClient;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeOnClickListener,HomeView {

    private RecyclerView daily;
    private RecyclerView.LayoutManager dailyLayoutManager;
    private TextView txtappName, txtqoute ,txtdaily;

    private APIClient client;
    private LocalSource localSource;
    private MealRepository repo;

    private DailyAdapter dailyAdapter;
    private HomePresenter presenter;
    private List<Meal> dailyMeal;

    private Handler handler;
    private Snackbar snackbar;
    private boolean mealsLoaded = false;
    private static final int CHECK_INTERVAL = 500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dailyMeal = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtappName = view.findViewById(R.id.appname);
        txtqoute = view.findViewById(R.id.appquote);
        txtdaily = view.findViewById(R.id.txtInsp);
        daily = view.findViewById(R.id.inspRecycler);

        dailyLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL , false);
        daily.setLayoutManager(dailyLayoutManager);

        client = APIClient.getInstance();
        localSource = LocalSource.getInstance(view.getContext());
        repo = MealRepository.getInstance(localSource , client);
        presenter = new HomePresenter(this , repo );

        presenter.loadMeals();



    }

    @Override
    public void onMealItemClicked(String id) {
       // Toast.makeText(getContext(), "Description will be shown", Toast.LENGTH_SHORT).show();
        Bundle args = new Bundle();
        args.putString("id", id);
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.action_HOmeFrag_to_detailFrag, args);
    }

    @Override
    public void onFavClicked(Meal meal) {
        presenter.addToFavourite(meal);
    }

    @Override
    public void showMeals(List<Meal> meals) {

        dailyAdapter = new DailyAdapter(meals ,getContext() , this) ;
        daily.setAdapter(dailyAdapter);


    }

    @Override
    public void showErr(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }


}