package com.joanneum.motivation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.joanneum.motivation.history.History;
import com.joanneum.motivation.profil.Profile;
import com.joanneum.motivation.quest.Quest;
import com.joanneum.motivation.ranking.Ranking;
import com.joanneum.motivation.reward.Reward;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String userName = prefs.getString("username", null);
        if (userName == null) {
            Intent intent = new Intent(getApplicationContext(), Registration.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);

        Button btnReward = (Button) findViewById(R.id.btnReward);
        btnReward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Reward.class);
                startActivity(intent);
            }
        });

        Button btnQuest = (Button) findViewById(R.id.btnQuest);
        btnQuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quest.class);
                startActivity(intent);
            }
        });

        Button btnHistory = (Button) findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), History.class);
                startActivity(intent);
            }
        });

        Button btnLeaderboard = (Button) findViewById(R.id.btnFriends);
        btnLeaderboard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Ranking.class);
                startActivity(intent);
            }
        });

        Button btnProfile = (Button) findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
            }
        });

    }
}