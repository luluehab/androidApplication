package com.example.luluchef.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.luluchef.R;
import com.example.luluchef.model.Meal;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.DailyViewHolder> {

    private List<Meal> dailyMeal;
    private Context context;
    private HomeOnClickListener onClick;

    public DailyAdapter(List<Meal> dailyMeal, Context context, HomeOnClickListener onClick) {
        this.dailyMeal = dailyMeal;
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public DailyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_item, parent, false);
        return new DailyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyViewHolder holder, int position) {
        Meal meal = dailyMeal.get(position);
        Glide.with(context).load(meal.getStrMealThumb()).apply(new RequestOptions().override(500,500).placeholder(R.drawable.ic_launcher_foreground)).into(holder.mealImg);
        holder.mealName.setText(meal.getStrMeal());
        holder.saveBtn.setOnClickListener(v -> Toast.makeText(v.getContext(), "will save", Toast.LENGTH_SHORT).show());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onDailyInspireItemClicked(meal.getIdMeal());
            }
        });
    }

    public void setList(ArrayList<Meal> myList) {
        this.dailyMeal = myList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return dailyMeal.size();
    }

    public class DailyViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImg, saveBtn;
        TextView mealName;

        public DailyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImg = itemView.findViewById(R.id.mealImg);
            saveBtn = itemView.findViewById(R.id.btnFav);
            mealName = itemView.findViewById(R.id.mealName);
        }
    }

}
