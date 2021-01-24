package com.joanneum.motivation.selection;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.joanneum.motivation.MainActivity;
import com.joanneum.motivation.R;

import java.time.LocalDate;

public class Success extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);
        View view = findViewById(R.id.layoutSuccess);
        TextView ep = findViewById(R.id.lblEP);
        ep.setText("You got " + getIntent().getStringExtra("EP") + "!!!");
        setContentView(view);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        addEP();
        addTaskToHistory();
    }

    //Todo:make sound
    private void addEP() {
        String taskEP = getIntent().getStringExtra("EP");
        String[] arrayEP = taskEP.split("EP");
        int ep = Integer.parseInt(arrayEP[0]);
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        int oldEP = prefs.getInt("EP", 0);
        editor.putInt("EP", oldEP + ep);
        editor.commit();
    }

    private void addTaskToHistory() {
        String taskName = getIntent().getStringExtra("name");
        String today = LocalDate.now().toString();
        String task = today + ":" + taskName;
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String oldTasks = prefs.getString("tasks", null);
        if (oldTasks != null) {
            task = oldTasks + ";" + task;
        }
        editor.putString("tasks", task);
        editor.commit();
    }
}