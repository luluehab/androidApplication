package com.example.luluchef.view;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luluchef.R;
import com.example.luluchef.R.id;
import com.example.luluchef.network.NetworkChecking;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.luluchef.network.Testing;
import java.util.List;

public class HostedActivity extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    public NavController navController;
    private static final String TAG = "HostedActivity";

    private Testing forTest = new Testing();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosted);
//check network
        if (NetworkChecking.isNetworkAvailable(this)) {
            forTest.testCategories();
            forTest.testIngredients();
            forTest.testCountries();
            forTest.testRandomMeal();
            forTest.testMealsByCategory();
            forTest.testMealsByIngredient();
            forTest.testMealsByArea();
        } else {
            Log.e(TAG, "Network is not available");
        }
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



}