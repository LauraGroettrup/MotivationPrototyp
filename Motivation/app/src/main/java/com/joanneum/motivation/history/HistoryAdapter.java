package com.joanneum.motivation.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joanneum.motivation.R;

import java.util.List;

public class HistoryAdapter extends
        RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<HistoryTask> tasks;

    // Pass in the task array into the constructor
    public HistoryAdapter(List<HistoryTask> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View historyView = inflater.inflate(R.layout.history_element, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(historyView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        HistoryTask task = tasks.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.taskTextView;
        textView.setText(task.getTasks());
        TextView textViewDate = holder.dateTextView;
        textViewDate.setText(task.getDate());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView taskTextView;
        public TextView dateTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            taskTextView = (TextView) itemView.findViewById(R.id.task);
            dateTextView = (TextView) itemView.findViewById(R.id.date);
        }
    }

}