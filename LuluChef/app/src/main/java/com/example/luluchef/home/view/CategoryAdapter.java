package com.example.luluchef.home.view;

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
import com.example.luluchef.R;
import com.example.luluchef.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>  {
    private List<Category> categoryItems;
    private Context context;
    private HomeOnClickListener onClick;
    private static final String TAG = "Category";
    public CategoryAdapter(List<Category> categoryItems, Context context, HomeOnClickListener onClick) {
        this.categoryItems = categoryItems;
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryItems.get(position);

        // Glide.with(context).load("https://flagsapi.com/" + country.getImageId() + "/flat/64.png").apply(new RequestOptions().override(500,500).placeholder(R.drawable.ic_launcher_foreground)).into(holder.ImginHome);
       // Glide.with(context).load(category.getStrCategoryThumb()).apply(new RequestOptions().override(500,500).placeholder(R.drawable.ic_launcher_foreground)).into(holder.ImginHome);

        Log.i(TAG, "onBindViewHolder: " + category.getStrCategoryThumb());
        Log.i(TAG, "onBindViewHolder: " + category.getStrCategory());
        Glide.with(context).load(category.getStrCategoryThumb())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.ImginHome);

        holder.txtName.setText(category.getStrCategory());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onCategoryItemClicked(category);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryItems.size();
    }

    public void setList(ArrayList<Category> myList) {

        this.categoryItems.addAll(myList);
        notifyDataSetChanged();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView ImginHome ;
        TextView txtName;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ImginHome = itemView.findViewById(R.id.ImginHome);
            txtName = itemView.findViewById(R.id.txtinHome);

        }
    }


}
