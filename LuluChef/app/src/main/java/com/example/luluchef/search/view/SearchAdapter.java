package com.example.luluchef.search.view;

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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Meal> mealList;
    private Context context;
    private SearchonClickListener onClick;

    public SearchAdapter(List<Meal> mealList, Context context, SearchonClickListener onClick) {
        this.mealList = mealList;
        this.context = context;
        this.onClick = onClick;
    }



    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.meal_item, parent, false);
    return new SearchViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Meal meal = mealList.get(position);
        Glide.with(context).load(meal.getStrMealThumb()).apply(new RequestOptions().override(500,500).placeholder(R.drawable.ic_launcher_foreground)).into(holder.mealImg);
        holder.mealName.setText(meal.getStrMeal());
        holder.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onFavClicked(meal);
                holder.saveBtn.setImageResource(R.drawable.heart_fill);
                Toast.makeText(context, meal.getStrMeal() + " added to favorite", Toast.LENGTH_SHORT).show();
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
            return mealList.size() ;
        }

        public void setList(List<Meal> meals) {
            this.mealList.clear();
            this.mealList = meals;
            notifyDataSetChanged();
        }
         public void clearMeals() {
             this.mealList.clear();
             notifyDataSetChanged();
         }


        public static class SearchViewHolder extends RecyclerView.ViewHolder {
            ImageView mealImg, saveBtn, calBtn;
            TextView mealName;

            public SearchViewHolder(@NonNull View itemView) {
                super(itemView);
                mealImg = itemView.findViewById(R.id.mealImg);
                saveBtn = itemView.findViewById(R.id.btnFav);
                mealName = itemView.findViewById(R.id.plannedmealname);
                calBtn = itemView.findViewById(R.id.btnCal);
            }
        }
}
