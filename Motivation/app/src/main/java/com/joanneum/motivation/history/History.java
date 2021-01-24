package com.joanneum.motivation.history;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joanneum.motivation.R;

import java.time.LocalDate;
import java.util.ArrayList;

public class History extends AppCompatActivity {

    ArrayList<HistoryTask> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        RecyclerView taskHistoryView = (RecyclerView) findViewById(R.id.history);

        SharedPreferences myPrefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String today = LocalDate.now().toString();
        String oldTasks = myPrefs.getString("tasks", today.toString() + ":Start with your first tasks today!");
        tasks = HistoryTask.createTaskList(oldTasks);
        HistoryAdapter adapter = new HistoryAdapter(tasks);
        taskHistoryView.setAdapter(adapter);
        taskHistoryView.setLayoutManager(new LinearLayoutManager(this));

    }

}
