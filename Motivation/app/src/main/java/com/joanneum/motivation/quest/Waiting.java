package com.joanneum.motivation.quest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.joanneum.motivation.R;
import com.joanneum.motivation.selection.Success;

public class Waiting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wait_for_quest);

        final String name = getIntent().getStringExtra("name");
        final String ep = getIntent().getStringExtra("EP");

        TextView task = findViewById(R.id.lblChosenTask);
        task.setText(name + "\n" + ep);

        Button btnFinished = (Button) findViewById(R.id.btnFinished);
        btnFinished.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Success.class);
                intent.putExtra("name", name);
                intent.putExtra("EP", ep);
                startActivity(intent);
            }
        });

    }
}
