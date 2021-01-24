package com.joanneum.motivation.selection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.joanneum.motivation.R;


public class CategorySelection extends AppCompatActivity {

    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection);

        adapter = new CategoryAdapter(this);
        GridView gridView = findViewById(R.id.gridSelection);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), TaskSelection.class);
                intent.putExtra("caller", getIntent().getStringExtra("caller"));
                intent.putExtra("category", position);
                startActivity(intent);
            }
        });

    }

}