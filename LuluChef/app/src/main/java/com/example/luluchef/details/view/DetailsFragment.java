package com.example.luluchef.details.view;



import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.luluchef.R;
import com.example.luluchef.database.LocalSource;
import com.example.luluchef.details.presenter.DetailPresenter;
import com.example.luluchef.model.IngredientModel;
import com.example.luluchef.model.Meal;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.APIClient;
import com.example.luluchef.planner.view.DayFragment;
import com.example.luluchef.view.HostedActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class DetailsFragment extends Fragment implements DetailsView , DetailOnClick {

   
    private ImageView mealImg;
    private TextView mealName, mealCountry, mealDesc ;
    private ImageButton toFav;
    private DetailOnClick onClick;
    private RecyclerView ingRecyclerView;
    private RecyclerView.LayoutManager detaileLayoutManager;
    private APIClient client;
    private LocalSource localSource;
    private MealRepository repo;
    private DetailPresenter detailPresenter;
    private YouTubePlayerView youTubePlayer;
    private IngrediantAdaptor ingrediantAdaptor;
    private DetailPresenter detailesPresenter;
    private Meal meal ;
    private int mSelectedIndex;
    String[] videoArray;
    String videoString;



    @Override
    public void onStart() {
        super.onStart();
        ((HostedActivity) requireActivity()).bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealImg = view.findViewById(R.id.detailsImageView);
        mealName = view.findViewById(R.id.detMealName);
        mealCountry= view.findViewById(R.id.detCountryName);
        mealDesc = view.findViewById(R.id.detailsDescriptionOfmeal);
        toFav = view.findViewById(R.id.detailsAddToFav);
        ingRecyclerView = view.findViewById(R.id.detailsIngredientRecycler);
        youTubePlayer = view.findViewById(R.id.youtubePlayer);
        detaileLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL , false);
        ingRecyclerView.setLayoutManager(detaileLayoutManager);

        ingrediantAdaptor = new IngrediantAdaptor(view.getContext());
        ingRecyclerView.setAdapter(ingrediantAdaptor);

        client = APIClient.getInstance();
        localSource = LocalSource.getInstance(view.getContext());
        repo = MealRepository.getInstance(localSource , client);
        detailPresenter = new DetailPresenter(this , repo );


        if (getArguments() != null) {
            String id = getArguments().getString("id");
            if (id != null) {
                detailPresenter.loadMealsInDetails(id);
            }
        }


    }

    @Override
    public void onResume() {
        super.onResume();

        ((HostedActivity) requireActivity()).bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void showDetails(Meal meal) {

        Glide.with(getContext()).load(meal.getStrMealThumb()).apply(new RequestOptions().override(500,500).placeholder(R.drawable.ic_launcher_foreground)).into(mealImg);
        mealName.setText(meal.getStrMeal());
        mealCountry.setText(meal.getStrArea());
        mealDesc.setText(meal.getStrInstructions());

        if (!meal.getStrYoutube().equals("")) {
            videoArray = meal.getStrYoutube().split("=");
            videoString = videoArray[1];
        } else {
            videoString = "";
        }


        youTubePlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                youTubePlayer.loadVideo(videoString, 0);
                youTubePlayer.pause();
            }
        });

        toFav.setOnClickListener(v -> {
            detailPresenter.addToFavourite(meal);
            Toast.makeText(getContext(), "added to favorite", Toast.LENGTH_SHORT).show();
        });


        ArrayList<IngredientModel> ingredientPojos = getIngList(meal);
        ingrediantAdaptor.setList(ingredientPojos);
        ingrediantAdaptor.notifyDataSetChanged();
    }

    private ArrayList<IngredientModel> getIngList(Meal meal) {
        ArrayList<IngredientModel> ingList = new ArrayList<>();
        for (int i =0 ; i <= 20; i++) {
            try {
                String ingredient = (String) meal.getClass().getMethod("getStrIngredient" + i).invoke(meal);
                String measure = (String) meal.getClass().getMethod("getStrMeasure" + i).invoke(meal);
                if (ingredient != null && !ingredient.isEmpty() && measure != null && !measure.isEmpty()) {
                    String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient + ".png";
                    ingList.add(new IngredientModel(ingredient, measure, imageUrl));
                }
            } catch (NoSuchMethodException  | InvocationTargetException | IllegalAccessException e )
            {
               // Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

            return ingList;
    }

    @Override
    public void showErr(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((HostedActivity) requireActivity()).bottomNavigationView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onFavClicked(Meal meal) {
        detailPresenter.addToFavourite(meal);

    }

    @Override
    public void ovCalClicked(Meal meal) {

    }


}
