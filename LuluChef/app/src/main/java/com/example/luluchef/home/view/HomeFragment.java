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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.luluchef.R;
import com.example.luluchef.database.LocalSource;
import com.example.luluchef.favourite.view.FavFragment;
import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.home.Presenter.HomePresenter;
import com.example.luluchef.model.Meal;
import com.example.luluchef.network.APIClient;
import com.example.luluchef.planner.Presenter.PlanPresenter;
import com.example.luluchef.planner.view.DayFragment;
import com.example.luluchef.planner.view.PlanFragment;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



public class HomeFragment extends Fragment implements HomeOnClickListener,HomeView {

    private AlertDialog networkAlertDialog;
    private RecyclerView daily,  countries , category;
    private RecyclerView.LayoutManager dailyLayoutManager,countryLayoutManager ,categoryLayoutManager;
    private TextView txtappName, txtqoute ,txtdaily;

    private APIClient client;
    private LocalSource localSource;
    private MealRepository repo;

    private DailyAdapter dailyAdapter;
    private CountryAdapter countryAdapter;
    private CategoryAdapter categoryAdapter;
    private HomePresenter presenter;
    private List<Meal> dailyMeal;
    private List<Country> countryList;
    private List<Category> categoryList;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dailyMeal = new ArrayList<>();
        countryList = new ArrayList<>();
        categoryList = new ArrayList<>();
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
        countries = view.findViewById(R.id.contRecycler);
        category = view.findViewById(R.id.cateRecycler);

        dailyLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL , false);
        daily.setLayoutManager(dailyLayoutManager);

        countryLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        countries.setLayoutManager(countryLayoutManager);

        categoryLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        category.setLayoutManager(categoryLayoutManager);

        client = APIClient.getInstance();
        localSource = LocalSource.getInstance(view.getContext());
        repo = MealRepository.getInstance(localSource , client);
        presenter = new HomePresenter(this , repo );

        presenter.loadMeals();
        presenter.getAllCountries();
        presenter.getAllCategories();



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
    public void onCalClicked(Meal meal) {
        DayFragment dialogFragment = new DayFragment(meal);
        dialogFragment.show(getFragmentManager(), "DayFragment");
    }


    @Override
    public void onCategoryItemClicked(Category category) {
        Bundle args = new Bundle();
        args.putString("filter", category.getStrCategory());
        args.putString("filterType", "category");
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.action_homeFrag_to_mealsFrag, args);
    }

    @Override
    public void onCountryItemClicked(Country country) {
        Bundle args = new Bundle();
        args.putString("filter", country.getStrArea());
        args.putString("filterType", "country");
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.action_homeFrag_to_mealsFrag, args);

    }





    private void showNoNetworkPopup() {

        if (networkAlertDialog != null && networkAlertDialog.isShowing()) {
            return; // Prevent creating a new dialog if one is already shown
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("No Network")
                .setMessage("Please check your connection, and try again")
                .setPositiveButton("Go to Favorites", new DialogInterface.OnClickListener() {
                    @Override
                    public void  onClick(DialogInterface dialog, int which) {
                        // Navigate to Favorite Fragment
                        NavController navController = Navigation.findNavController(getView());
                        navController.navigate(R.id.action_homeFrag_to_favFrag);
                       // navigateToFragment(new FavFragment());
                        dialog.dismiss();
                    }
                }).setNeutralButton("try again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Navigate to Calendar Fragment
                        //navigateToFragment(new PlanFragment());
                        NavController navController = Navigation.findNavController(getView());
                        navController.navigate(R.id.action_currentFrag_to_homeFrag);
                        dialog.dismiss();
                    }})
                .setNegativeButton("Go to Calendar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Navigate to Calendar Fragment
                        //navigateToFragment(new PlanFragment());
                        NavController navController = Navigation.findNavController(getView());
                        navController.navigate(R.id.action_currentFragment_to_calendarFragment);
                        dialog.dismiss();
                    }
                })
                .setCancelable(false); // Prevent dialog from being dismissed on outside touch

        networkAlertDialog = builder.create();
        networkAlertDialog.show();

    }
    private void navigateToFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Hostfragment, fragment); // Use your actual container ID
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    @Override
    public void showMeals(List<Meal> meals) {

        dailyAdapter = new DailyAdapter(meals ,getContext() , this) ;
        daily.setAdapter(dailyAdapter);


    }

    @Override
    public void showErr(String error) {
        showNoNetworkPopup();
        //Toast.makeText(getContext(), "No Network", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCountries(List<Country> country) {
        countryAdapter = new CountryAdapter(country, getContext(), this);
        countries.setAdapter(countryAdapter);
    }

    @Override
    public void showCategories(List<Category> categories) {
        categoryAdapter = new CategoryAdapter(categories, getContext() , this);
        category.setAdapter(categoryAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}