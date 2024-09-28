package com.example.luluchef.planner.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.luluchef.R;
import com.example.luluchef.favourite.view.FavOnClickListener;
import com.example.luluchef.model.PlanedMeal;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.planned_item, parent, false);
        return new PlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        PlanedMeal meal = plannedMeals.get(position);
        holder.mealName.setText(meal.getIdMeal());
        holder.mealDate.setText(meal.getDate().toString()); // Format the date if needed
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onDelClicked(meal);
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
        ImageView btnDelete;
        public PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.plannedmealname);
            mealDate = itemView.findViewById(R.id.plannedmealdate);
            btnDelete = itemView.findViewById(R.id.planBtndelete);
        }
    }
}
