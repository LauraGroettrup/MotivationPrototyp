package com.joanneum.motivation.selection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.joanneum.motivation.R;
import com.joanneum.motivation.quest.Waiting;

public class TaskSelection extends AppCompatActivity {
    TaskAdapter adapter;
    String[] tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection);

        // Household,Education,Work,Social,Fitness,Health,Hobbys,Special
        switch (getIntent().getIntExtra("category", -1)) {
            case 0:
                tasks = new String[]{"Dishes-5EP", "Sweeping-10EP", "Vacuuming-10EP", "Feeding Pets-5EP", "Doing Laundry-20EP", "Cooking-5EP", "Grocery shopping-5EP"};
                break;
            case 1:
                tasks = new String[]{"Learning-10EP", "Visiting lectures-5EP", "Doing Homework-10EP", "Make a ToDo List-5EP", "Organize documents-5EP"};
                break;
            case 2:
                tasks = new String[]{"Go to office-15EP", "Enter you hours-5EP", "Researched Something-10EP", "Commited you code-5EP", "Wrote Email-5EP"};
                break;
            case 3:
                tasks = new String[]{"Visit friends-5EP", "Helped somebody unfamiliar-20EP", "Called family-5EP", "Nice small talk-10EP"};
                break;
            case 4:
                tasks = new String[]{"Going to a course-20EP", "Using the steps-5EP", "Home workout-10EP", "Go for a walk-5EP", "Go for a run-15EP"};
                break;
            case 5:
                tasks = new String[]{"Brushing your teeth-5EP", "Making a doctor appointment-15EP", "Going to a doctor appointment-15EP", "Eat healthy-10EP", "Using this app once a day-5EP"};
                break;
            case 6:
                tasks = new String[]{"Investing time in your hobby-5EP", "Research about your hobby-10EP", "Joined a group with the same hobby-15EP"};
                break;
            case 7:
                tasks = new String[]{"Present the app-520EP"};
                break;
            default:
                System.out.println("Something went wrong trying to get category");
        }

        adapter = new TaskAdapter(this, tasks);
        GridView gridView = findViewById(R.id.gridSelection);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                if (getIntent().getStringExtra("caller").equals("reward")) {
                    intent = new Intent(getApplicationContext(), Success.class);
                } else {
                    intent = new Intent(getApplicationContext(), Waiting.class);
                }
                TextView txtName = view.findViewById(R.id.txtTaskName);
                TextView txtEP = view.findViewById(R.id.txtTaskEP);
                String name = txtName.getText().toString();
                String EP = txtEP.getText().toString();
                intent.putExtra("name", name);
                intent.putExtra("EP", EP);
                startActivity(intent);
            }
        });

    }
}
