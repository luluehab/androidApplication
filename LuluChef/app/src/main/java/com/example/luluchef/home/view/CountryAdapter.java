package com.example.luluchef.home.view;

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
import com.example.luluchef.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> countryItems;
    private Context context;
    private OnCountryClick onClick;


    public CountryAdapter(List<Country> countryItems, Context context) {
        this.countryItems = countryItems;
        this.context = context;
        //this.onClick = onClick;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_item, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
         Country country = countryItems.get(position);

       // Glide.with(context).load("https://flagsapi.com/" + country.getImageId() + "/flat/64.png").apply(new RequestOptions().override(500,500).placeholder(R.drawable.ic_launcher_foreground)).into(holder.ImginHome);
     /*   Glide.with(context)
                .load("https://flagsapi.com/" + country.getImageId() + "/flat/64.png")
                .apply(new RequestOptions()
                        .override(500, 500)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground))  // Fallback in case of error
                .into(holder.ImginHome);*/

        Glide.with(context)
                .load("https://flagsapi.com/" + country.getImageId() + "/flat/64.png")
                .apply(new RequestOptions()
                        .override(500, 500)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground))  // Fallback in case of error
                .into(holder.ImginHome);

        holder.txtName.setText(country.getStrArea());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClick.onClick(country);
                Toast.makeText(v.getContext(), "Country meals will be Show", Toast.LENGTH_SHORT).show();;
            }
        });
    }

    public void setList(ArrayList<Country> myList) {

        this.countryItems.addAll(myList);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return countryItems.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

        ImageView ImginHome ;
        TextView txtName;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ImginHome = itemView.findViewById(R.id.ImginHome);
            txtName = itemView.findViewById(R.id.txtinHome);

        }
    }

    interface OnCountryClick {
        void onClick(Country country);
    }
}
