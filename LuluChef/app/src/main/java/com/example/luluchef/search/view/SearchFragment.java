package com.example.luluchef.search.view;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luluchef.R;
import com.example.luluchef.favourite.view.FavFragment;
import com.example.luluchef.network.NetworkChecking;
import com.example.luluchef.planner.view.PlanFragment;

public class SearchFragment extends Fragment {

    private AlertDialog networkAlertDialog;

    @Override
    public void onStart() {
        super.onStart();
        if (!NetworkChecking.isNetworkAvailable(getContext())) {
            showNoNetworkPopup();
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
    public void onDestroy() {
        super.onDestroy();
    }


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
}