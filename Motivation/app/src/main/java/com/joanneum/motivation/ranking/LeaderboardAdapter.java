package com.joanneum.motivation.ranking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joanneum.motivation.R;

import java.util.List;

public class LeaderboardAdapter extends
        RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private List<Place> places;

    // Pass in the task array into the constructor
    public LeaderboardAdapter(List<Place> places) {
        this.places = places;
    }

    @NonNull
    @Override
    public LeaderboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View placeView = inflater.inflate(R.layout.place, parent, false);

        // Return a new holder instance
        LeaderboardAdapter.ViewHolder viewHolder = new LeaderboardAdapter.ViewHolder(placeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardAdapter.ViewHolder holder, int position) {
        Place place = places.get(position);

        // Set item views based on your views and data model
        TextView textViewPlace = holder.placeTextView;
        textViewPlace.setText(Integer.toString(place.getPlace()));
        TextView textViewName = holder.nameTextView;
        textViewName.setText(place.getName());
        TextView textViewScore = holder.scoreTextView;
        textViewScore.setText(Integer.toString(place.getScore()));
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView placeTextView;
        public TextView nameTextView;
        public TextView scoreTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            placeTextView = (TextView) itemView.findViewById(R.id.place);
            nameTextView = (TextView) itemView.findViewById(R.id.name);
            scoreTextView = (TextView) itemView.findViewById(R.id.score);
        }
    }

}