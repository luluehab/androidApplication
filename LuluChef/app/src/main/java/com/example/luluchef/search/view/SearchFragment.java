package com.example.luluchef.search.view;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luluchef.R;
import com.example.luluchef.database.LocalSource;
import com.example.luluchef.favourite.Presenter.FavPresenter;
import com.example.luluchef.favourite.view.FavFragment;
import com.example.luluchef.model.Category;
import com.example.luluchef.model.Country;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.APIClient;
import com.example.luluchef.network.NetworkChecking;
import com.example.luluchef.planner.view.DayFragment;
import com.example.luluchef.planner.view.PlanAdapter;
import com.example.luluchef.planner.view.PlanFragment;
import com.example.luluchef.search.Filter.view.FilterFragment;
import com.example.luluchef.search.Filter.view.FilterOnClickListener;
import com.example.luluchef.search.Presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchView , SearchonClickListener , FilterOnClickListener {

    private static final String TAG = "lolo";
    private AlertDialog networkAlertDialog;
    private android.widget.SearchView svSearch;
    private ImageView filter;
    private RecyclerView searchRecycler;
    private SearchPresenter presenter;
    private APIClient client;
    private LocalSource localSource;
    private MealRepository repo;
    private SearchAdapter adapter;
    ArrayList<Meal> emptyMeals = new ArrayList<>();
    private String category, country, ingredient;
    @Override
    public void onStart() {
        super.onStart();
        if (!NetworkChecking.isNetworkAvailable(getContext())) {
            showNoNetworkPopup();
        }
        if (category != null || country != null || ingredient != null) {
            filter.setColorFilter(ContextCompat.getColor(requireContext(), R.color.lulu));
        } else {
            filter.setColorFilter(ContextCompat.getColor(requireContext(), R.color.gray_400));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filter = view.findViewById(R.id.filterImg);
        svSearch = view.findViewById(R.id.etSearch);
        searchRecycler = view.findViewById(R.id.searchRecycler);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        searchRecycler.setLayoutManager(linearLayoutManager);


        client = APIClient.getInstance();
        localSource = LocalSource.getInstance(view.getContext());
        repo = MealRepository.getInstance(localSource , client);
        presenter = new SearchPresenter(this , repo );


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FilterFragment.getInstance(getParentFragmentManager(), SearchFragment.this, category, country, ingredient).showFilter();

            }
        });
        svSearch.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // I ignore this cause i want live search only
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                    if (s.length() == 1) {
                        // Search by the first character
                        Log.i(TAG, "onQueryTextChange: "+ s);
                        presenter.getMealByFirstChar(s,category,country,ingredient);
                    } else if (s.length() > 1) {
                        // Search by full meal name
                        adapter.clearMeals();
                        presenter.getMealByName(s , category,country,ingredient);
                    } else {
                        // If the input is cleared, you can show all meals or an empty state
                        adapter.clearMeals(); // Clear the list
                    }


                return false;
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showMeals(List<Meal> meals) {
            adapter = new SearchAdapter(meals, getContext(), this);
            searchRecycler.setAdapter(adapter);
    }

    @Override
    public void showErr(String error) {

        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCountries(List<Country> countries) {

    }

    @Override
    public void showCategories(List<Category> categories) {

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
        presenter.addToFavourite(meal);
    }

    @Override
    public void onCalClicked(Meal meal) {
        DayFragment dialogFragment = new DayFragment(meal);
        dialogFragment.show(getFragmentManager(), "DayFragment");
    }

    @Override
    public void onSearchClick(Meal meal) {

    }



    //////////////////////////////////////////////////////////////
    private void showNoNetworkPopup() {

        if (networkAlertDialog != null && networkAlertDialog.isShowing()) {
            return; // Prevent creating a new dialog if one is already shown
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("No Network")
                .setMessage("Please check your connection.")
                .setPositiveButton("Go to Favorites", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Navigate to Favorite Fragment
                        navigateToFragment(new FavFragment());
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Go to Calendar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Navigate to Calendar Fragment
                        navigateToFragment(new PlanFragment());
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
    public void onFilterClick(String category, String country, String ingredient) {
        this.category = category;
        this.country = country;
        this.ingredient = ingredient;
        if (category != null || country != null || ingredient != null) {
            filter.setColorFilter(ContextCompat.getColor(requireContext(), R.color.lulu));
        } else {
            filter.setColorFilter(ContextCompat.getColor(requireContext(), R.color.gray_400));
        }
        FilterFragment.getInstance(getParentFragmentManager(), this, category, country, ingredient).dismissFilter();

    }


}