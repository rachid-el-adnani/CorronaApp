package com.example.coronaapp.HelperClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.coronaapp.R;

import java.util.ArrayList;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.viewHolder> {

    private ArrayList<TpisHelperClass> tpisHelperArray;

    public TipsAdapter(ArrayList<TpisHelperClass> tpisHelperArray) {
        this.tpisHelperArray = tpisHelperArray;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tip_card_design, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        TpisHelperClass tpisHelperClass = tpisHelperArray.get(position);

        //Setting params
        holder.image.setImageResource(tpisHelperClass.getImage());
        holder.title.setText(tpisHelperClass.getTitle());
        holder.desc.setText(tpisHelperClass.getDesc());

    }

    @Override
    public int getItemCount() {

        return tpisHelperArray.size();

    }


    static class viewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;

        viewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.featured_image);
            title = itemView.findViewById(R.id.featured_title);
            desc = itemView.findViewById(R.id.featured_dsec);

        }

    }

}
