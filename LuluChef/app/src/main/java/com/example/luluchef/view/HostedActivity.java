package com.example.luluchef.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.luluchef.R;
import com.example.luluchef.R.id;
import com.example.luluchef.favourite.view.FavFragment;
import com.example.luluchef.home.view.HomeFragment;
import com.example.luluchef.planner.view.PlanFragment;
import com.example.luluchef.search.view.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HostedActivity extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    public NavController navController;
    private static final String TAG = "HostedActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosted);
        navController = Navigation.findNavController(this, R.id.Hostfragment);
        bottomNavigationView = findViewById(R.id.bottomNavigator);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();  // Get the item ID
                if (itemId == R.id.favFrag) {
                    // Respond to navigation item 1 click
                    Log.i(TAG, "onNavigationItem one (fav) Selected: ");
                    navController.navigate(id.favFrag);
                    return true;
                } else if (itemId == R.id.searchFrag) {
                    // Respond to navigation item 2 click
                    Log.i(TAG, "onNavigationItem (search) Selected: ");
                    navController.navigate(id.searchFrag);
                    return true;
                } else if (itemId == R.id.homeFrag) {
                    // Respond to navigation item 3 click
                    Log.i(TAG, "onNavigationItem (home) Selected: ");
                    navController.navigate(id.homeFrag);
                    return true;
                } else if (itemId == id.planFrag) {
                    // Respond to navigation item 4 click
                    Log.i(TAG, "onNavigationItem (plan) Selected: ");
                    navController.navigate(id.planFrag);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        NavController navController = Navigation.findNavController(this, R.id.Hostfragment);
        if (navController.getCurrentDestination().getId() == R.id.homeFrag) {
            // If the user is in the HomeFragment, close the app
            finish();
        }
        else if(navController.getCurrentDestination().getId() == id.favFrag)
        {
            finish();
        }
        else if(navController.getCurrentDestination().getId() == id.searchFrag)
        {
            finish();
        }
        else if(navController.getCurrentDestination().getId() == id.planFrag)
        {
            finish();
        }
        else {
            // Otherwise, just handle the default back action (returning to previous fragments)
            super.onBackPressed();
        }
    }
}