package com.example.luluchef.favourite.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.luluchef.R;
import com.example.luluchef.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder> {
    private List<Meal> favMeal;
    private Context context;
    private  FavOnClickListener onClick;

    public FavAdapter(List<Meal> favMeal, Context context, FavOnClickListener onClick) {
        this.favMeal = favMeal;
        this.context = context;
        this.onClick = onClick;
    }
    @NonNull
    @Override
    public FavAdapter.FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_item, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.FavViewHolder holder, int position) {
        Meal meal = favMeal.get(position);
        Glide.with(context).load(meal.getStrMealThumb()).apply(new RequestOptions().override(500,500).placeholder(R.drawable.ic_launcher_foreground)).into(holder.mealImg);
        holder.saveBtn.setImageResource(R.drawable.heart_fill);
        holder.mealName.setText(meal.getStrMeal());
        holder.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onFavClicked(meal);
                Toast.makeText(context, meal.getStrMeal() + " removed from favorite", Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onMealItemClicked(meal.getIdMeal());
            }
        });

        holder.calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onCalClicked(meal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favMeal.size() ;
    }

    public void setList(ArrayList<Meal> myList) {
        this.favMeal = myList;
        notifyDataSetChanged();
    }


    public static class FavViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImg, saveBtn, calBtn;
        TextView mealName;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImg = itemView.findViewById(R.id.mealImg);
            saveBtn = itemView.findViewById(R.id.btnFav);
            mealName = itemView.findViewById(R.id.plannedmealname);
            calBtn = itemView.findViewById(R.id.btnCal);
        }
    }

}
