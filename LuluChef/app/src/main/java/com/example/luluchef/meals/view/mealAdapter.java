package com.example.luluchef.meals.view;

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

public class mealAdapter extends RecyclerView.Adapter<mealAdapter.MealViewHolder>{
    private List<Meal> myList = new ArrayList<>();
    private Context context;
    private onMealClick onMealClick;


    public mealAdapter(List<Meal> myList, Context context, com.example.luluchef.meals.view.onMealClick onMealClick) {
        this.myList = myList;
        this.context = context;
        this.onMealClick = onMealClick;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_item, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal meal = myList.get(position);
        Glide.with(context).load(meal.getStrMealThumb()).apply(new RequestOptions().override(500,500).placeholder(R.drawable.ic_launcher_foreground)).into(holder.mealImg);
        holder.mealName.setText(meal.getStrMeal());
        holder.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.saveBtn.setImageResource(R.drawable.heart_fill);
                onMealClick.onSaveBtnClicked(meal);
                Toast.makeText(context, meal.getStrMeal() + " Saved to favorite", Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealClick.onMealItemClicked(meal.getIdMeal());
            }
        });

        holder.calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMealClick.onCalBtnClicked(meal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void setList(List<Meal> myList) {
        this.myList = myList;
        notifyDataSetChanged();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {

        ImageView mealImg, saveBtn, calBtn;
        TextView mealName;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImg = itemView.findViewById(R.id.mealImg);
            saveBtn = itemView.findViewById(R.id.btnFav);
            mealName = itemView.findViewById(R.id.plannedmealname);
            calBtn = itemView.findViewById(R.id.btnCal);
        }
    }
}
