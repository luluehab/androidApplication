package com.example.luluchef.meals.view;

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
import android.widget.Toast;

import com.example.luluchef.R;
import com.example.luluchef.database.LocalSource;
import com.example.luluchef.favourite.Presenter.FavPresenter;
import com.example.luluchef.meals.presenter.mealPresenter;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.APIClient;
import com.example.luluchef.planner.view.DayFragment;

import java.util.List;


public class MealFragment extends Fragment implements onMealClick, mealView {

    private RecyclerView mealsRecycler;
    private RecyclerView.LayoutManager mealLayoutManager;
    String filter, filterType;
    private APIClient client;
    private LocalSource localSource;
    private MealRepository repo;
    private mealAdapter mealAdapter;
    private mealPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealsRecycler = view.findViewById(R.id.mealRecycler);
        mealLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL , false);
        mealsRecycler.setLayoutManager(mealLayoutManager);
        client = APIClient.getInstance();
        localSource = LocalSource.getInstance(view.getContext());
        repo = MealRepository.getInstance(localSource , client);
        presenter = new mealPresenter(repo , this );

        Bundle args = getArguments();
        if (args != null) {
            filter = args.getString("filter");
            filterType = args.getString("filterType");
            Log.d("MAI", "onViewCreated: " + filter + " " + filterType);

            if ("country".equals(filterType)) {
                presenter.getCountryMeals(filter);
            } else {
                presenter.getCategoryMeals(filter);
            }
        }



    }

    @Override
    public void showDetails(List<Meal> meal) {

        mealAdapter = new mealAdapter(meal,getContext(),this);
        mealsRecycler.setAdapter(mealAdapter);
    }

    @Override
    public void showErr(String error) {
        Toast.makeText(getContext(), "no network", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveBtnClicked(Meal meal) {
        presenter.insertMealToFavourite(meal);
    }

    @Override
    public void onMealItemClicked(String id) {
        Bundle args = new Bundle();
        args.putString("id", id);
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.action_HOmeFrag_to_detailFrag, args);
    }

    @Override
    public void onCalBtnClicked(Meal meal) {
        DayFragment dialogFragment = new DayFragment(meal);
        dialogFragment.show(getFragmentManager(), "DayFragment");
    }
}