package com.example.luluchef.home.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.luluchef.R;


public class NoNetworkFragment extends DialogFragment {

    private Button cal , fav;
    private String message;
    public NoNetworkFragment(String message) {
            this.message = message;
    }

   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_network, container, false);

    }

  @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cal = view.findViewById(R.id.gocalButton);
        fav = view.findViewById(R.id.goFavButton);
       /* NavController navController = Navigation.findNavController(getActivity(), R.id.Hostfragment);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                navController.navigate(R.id.action_homeFrag_to_CALFrag, args);
                navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
                    @Override
                    public void onDestinationChanged(@NonNull NavController controller, @NonNull androidx.navigation.NavDestination destination, @Nullable Bundle arguments) {
                        // Remove the dialog fragment using FragmentManager
                        FragmentManager fragmentManager = getParentFragmentManager();
                        if (fragmentManager != null) {
                            Log.d("NoNetworkFragment", "Dialog is visible, removing from FragmentManager.");
                            fragmentManager.beginTransaction().remove(NoNetworkFragment.this).commit();
                        }
                        // Remove listener to avoid multiple triggers
                        navController.removeOnDestinationChangedListener(this);
                    }
                });
            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                navController.navigate(R.id.action_homeFrag_to_FAVFrag, args);
                dismiss();


            }
        });*/
      AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
      builder.setMessage("No Network Connection")
              .setTitle("Network Alert")
              .setPositiveButton("OK", (dialog, id) -> {
                  // Dismiss dialog or any other action
              });
      builder.create().show();
    }


}