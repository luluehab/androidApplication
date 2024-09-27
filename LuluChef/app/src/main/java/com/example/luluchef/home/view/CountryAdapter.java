/*package com.example.luluchef.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luluchef.R;
import com.example.luluchef.model.Country;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> countryItems;
    private Context context;
    private HomeOnClickListener onClick;

    public CountryAdapter(List<Country> countryItems, Context context, HomeOnClickListener onClick) {
        this.countryItems = countryItems;
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_item, parent, false);
        return new CountryAdapter.CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country country = countryItems.get(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
*/