package com.example.luluchef.favourite.view;

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
import com.example.luluchef.favourite.Presenter.FavPresenter;
import com.example.luluchef.home.Presenter.HomePresenter;
import com.example.luluchef.home.view.DailyAdapter;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.APIClient;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class FavFragment extends Fragment implements FavOnClickListener , FavView {


    private RecyclerView fav;
    private RecyclerView.LayoutManager favLayoutManager;
    private TextView txtfav;

    private APIClient client;
    private LocalSource localSource;
    private MealRepository repo;
    private FavAdapter favAdapter;
    private FavPresenter presenter;
    private List<Meal> favMeal;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtfav = view.findViewById(R.id.txtFav);
        fav =  view.findViewById(R.id.favView);
        favLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL , false);
        fav.setLayoutManager(favLayoutManager);

        client = APIClient.getInstance();
        localSource = LocalSource.getInstance(view.getContext());
        repo = MealRepository.getInstance(localSource , client);
        presenter = new FavPresenter(this , repo );

        presenter.loadMeals();
    }

    @Override
    public void onMealItemClicked(String id) {
        Bundle args = new Bundle();
        args.putString("id", id);
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.action_HOmeFrag_to_detailFrag, args);
    }

    @Override
    public void onFavClicked(Meal meal) {
            presenter.removeFromFavourite(meal);
    }

    @Override
    public void showMeals(List<Meal> meals) {
        favAdapter = new FavAdapter(meals,getContext(), this);
        fav.setAdapter(favAdapter);
    }

    @Override
    public void showErr(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
