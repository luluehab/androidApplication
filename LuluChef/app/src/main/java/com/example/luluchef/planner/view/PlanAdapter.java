package com.example.luluchef.planner.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
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
import com.example.luluchef.favourite.view.FavOnClickListener;
import com.example.luluchef.model.PlanedMeal;

import java.util.ArrayList;
import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHolder>{
    private List<PlanedMeal> plannedMeals;
    private Context context;
    private onPlanClickListener onClick;

    public PlanAdapter(List<PlanedMeal> plannedMeals, Context context, onPlanClickListener onClick) {
        this.plannedMeals = plannedMeals;
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        return new PlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        PlanedMeal meal = plannedMeals.get(position);
        holder.mealName.setText(meal.getMeal().getStrMeal());
        holder.mealDate.setText(meal.getDate().toString());// Format the date if needed
        holder.mealType.setText(meal.getMeal().getStrCategory());
        holder.mealArea.setText(meal.getMeal().getStrArea());
        Glide.with(context).load(meal.getMeal().getStrMealThumb()).apply(new RequestOptions().override(500,500).placeholder(R.drawable.ic_launcher_foreground)).into(holder.mealImg);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClick.onDelClicked(meal);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onMealItemClicked(meal.getIdMeal());
            }
        });
    }

    @Override
    public int getItemCount() {
        return plannedMeals.size();
    }

    public static class PlanViewHolder extends RecyclerView.ViewHolder {
        TextView mealName;
        TextView mealDate;
        TextView mealArea;
        TextView mealType;
        ImageView btnDelete;
        ImageView mealImg;
        public PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.planmealName);
            mealDate = itemView.findViewById(R.id.planmealDate);
            mealArea = itemView.findViewById(R.id.planmealArea);
            mealType = itemView.findViewById(R.id.planType);
            mealImg = itemView.findViewById(R.id.planmealImg);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateMeals(List<PlanedMeal> newMealPlans) {
        this.plannedMeals = newMealPlans;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void clearMeals() {
        this.plannedMeals = new ArrayList<>();
        notifyDataSetChanged();
    }
}
