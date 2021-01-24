package com.joanneum.motivation.quest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.joanneum.motivation.R;
import com.joanneum.motivation.selection.CategorySelection;

public class Quest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut_quest);

        Button btnChose = findViewById(R.id.btnChooseQuest);
        btnChose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategorySelection.class);
                intent.putExtra("caller", "quest");
                startActivity(intent);
            }
        });

    }
}
