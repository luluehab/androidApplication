package com.example.luluchef.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luluchef.R;


public class HomeFragment extends Fragment {

    private RecyclerView daily,country,category;
    private TextView txtappName, txtqoute ,txtcountry, txtcategory, txtcaloric;
    private ImageView logo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        daily = view.findViewById(R.id.inspRecycler);
        country = view.findViewById(R.id.contRecycler);
        category = view.findViewById(R.id.cateRecycler);
        txtappName = view.findViewById(R.id.appname);
        txtqoute = view.findViewById(R.id.appquote);
        logo = view.findViewById(R.id.logoImg);
    }
}