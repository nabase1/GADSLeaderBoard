package com.nabase1.gadsleaderboard.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nabase1.gadsleaderboard.R;
import com.nabase1.gadsleaderboard.modals.Learners;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class LeanerBoardAdapter extends RecyclerView.Adapter<LeanerBoardAdapter.LeanerBoardViewHolder> {

    ArrayList<Learners> mArrayList;
    ImageView mImageView;
    public LeanerBoardAdapter(ArrayList<Learners> learners) {
        this.mArrayList = learners;
    }

    @NonNull
    @Override
    public LeanerBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_items, parent, false);

        return new LeanerBoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeanerBoardViewHolder holder, int position) {
        Learners learners = mArrayList.get(position);
        holder.bind(learners);

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class LeanerBoardViewHolder extends RecyclerView.ViewHolder {
        TextView name, hours;

        public LeanerBoardViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView_name);
            hours = itemView.findViewById(R.id.textView_hours);
            mImageView = itemView.findViewById(R.id.imageView2);
        }

        public void bind(Learners learners){
            String learning = "learning hours";
            String time_country = learners.getHours() + " " + learning +  ", " + learners.getCountry();

            showImage(learners.getImageUrl());
            name.setText(learners.getName());
            hours.setText(time_country);
        }
    }

    public void showImage(String url){
        if(url != null && url.isEmpty() == false){
            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            Picasso.with(mImageView.getContext())
                    .load(url)
                    .resize(100, 100)
                    .centerCrop()
                    .into(mImageView);
            Log.d("uri not null", url);
        }

    }
}
