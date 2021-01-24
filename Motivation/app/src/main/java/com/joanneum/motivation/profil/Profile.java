package com.joanneum.motivation.profil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.joanneum.motivation.R;

public class Profile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        int ep = prefs.getInt("EP", 0);
        String name = prefs.getString("username", "");
        TextView overview = (TextView) findViewById(R.id.lblProfil);
        TextView username = (TextView) findViewById(R.id.lblUsername);
        overview.setText(name);
        username.setText("Username: " + name);
        final float newLevel = calculateLevelProgress(ep);
        ImageView smallCircle = (ImageView) findViewById(R.id.imgCircle);
        animateProgress(smallCircle, newLevel);
    }

    private float calculateLevelProgress(int ep) {
        TextView levelLbl = (TextView) findViewById(R.id.lblLevel);
        TextView nextLvlLbl = (TextView) findViewById(R.id.lblEpToNextLvl);
        int level = (int) Math.ceil(ep / 50.0);
        levelLbl.setText("Level: " + level);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        int progress = (50 * level - ep);
        nextLvlLbl.setText("EP to next lvl: " + progress);
        ProgressBarAnimation anim = new ProgressBarAnimation(progressBar, 0, progress);
        anim.setDuration(1000);
        progressBar.startAnimation(anim);
        return level;
    }

    private void animateProgress(View view, float newLevel) {
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        float oldLevel = prefs.getFloat("level", 0f);
        float sizeFactorStart = 0.05f * oldLevel + 1.0f;
        float sizeFactorEnd = 0.05f * newLevel + 1.0f;
        ScaleAnimation fade_in = new ScaleAnimation(sizeFactorStart, sizeFactorEnd, sizeFactorStart, sizeFactorEnd, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        fade_in.setDuration(1000);
        fade_in.setFillAfter(true);
        view.setVisibility(View.VISIBLE);
        view.startAnimation(fade_in);
    }
}
