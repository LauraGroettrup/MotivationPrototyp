package com.joanneum.motivation.ranking;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.joanneum.motivation.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ranking extends AppCompatActivity {

    private final DocumentReference leaderboardDB = FirebaseFirestore.getInstance().document("leaderboard/o0p2TnYuUs14Dak9woKT");
    private final ArrayList<Place> leaderboard = new ArrayList<Place>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readLeaderboard();
        setContentView(R.layout.leaderboard);
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        int oldEP = prefs.getInt("EP", 0);
        TextView score = findViewById(R.id.lblYourScore);
        score.setText("Your score: " + oldEP);
    }

    private void readLeaderboard() {
        leaderboardDB
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()) {
                            DocumentSnapshot snap = task.getResult();
                            Map<String, Object> map = snap.getData();
                            ArrayList first = (ArrayList) map.get("firstPlace");
                            leaderboard.add(new Place(1, (String) first.get(0), (Long) first.get(1)));
                            ArrayList second = (ArrayList) map.get("secondPlace");
                            leaderboard.add(new Place(2, (String) second.get(0), (Long) second.get(1)));
                            ArrayList third = (ArrayList) map.get("thirdPlace");
                            leaderboard.add(new Place(3, (String) third.get(0), (Long) third.get(1)));
                            ArrayList fourth = (ArrayList) map.get("fourthPlace");
                            leaderboard.add(new Place(4, (String) fourth.get(0), (Long) fourth.get(1)));
                            ArrayList fifth = (ArrayList) map.get("fifthPlace");
                            leaderboard.add(new Place(5, (String) fifth.get(0), (Long) fifth.get(1)));
                            ArrayList sixth = (ArrayList) map.get("sixthPlace");
                            leaderboard.add(new Place(6, (String) sixth.get(0), (Long) sixth.get(1)));
                            ArrayList seventh = (ArrayList) map.get("seventhPlace");
                            leaderboard.add(new Place(7, (String) seventh.get(0), (Long) seventh.get(1)));
                            ArrayList eight = (ArrayList) map.get("eightPlace");
                            leaderboard.add(new Place(8, (String) eight.get(0), (Long) eight.get(1)));
                            ArrayList ninth = (ArrayList) map.get("ninthPlace");
                            leaderboard.add(new Place(9, (String) ninth.get(0), (Long) ninth.get(1)));
                            ArrayList tenth = (ArrayList) map.get("tenthPlace");
                            leaderboard.add(new Place(10, (String) tenth.get(0), (Long) tenth.get(1)));

                            checkPosition();
                        } else {
                            Log.w("DB", "Error getting documents.");
                        }
                    }
                });
    }

    private void checkPosition() {

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        int yourScore = prefs.getInt("EP", 0);
        String yourName = prefs.getString("username", "Anonym");
        int yourOldPlace = 9;
        int newPlace = -1;
        for (int i = 0; i < leaderboard.size(); i++) {
            if (leaderboard.get(i).getName().equals(yourName)) {
                yourOldPlace = i;
            }
        }
        for (int i = 0; i < leaderboard.size(); i++) {
            if (leaderboard.get(i).getScore() <= yourScore) {
                newPlace = i + 1;
                if (yourOldPlace != 9) {
                    //ascended
                    if (i < yourOldPlace) {
                        for (int j = yourOldPlace; j > i; j--) {
                            Place placeToShiftDown = leaderboard.get(j - 1);
                            placeToShiftDown.setPlace(j + 1);
                            leaderboard.set(j, placeToShiftDown);
                        }
                    }
                    //descended
                    else if (yourOldPlace < i) {
                        for (int j = yourOldPlace; j < i; j++) {
                            Place placeToShiftUp = leaderboard.get(j + 1);
                            placeToShiftUp.setPlace(j + 1);
                            leaderboard.set(j, placeToShiftUp);
                        }
                    }
                } else {
                    for (int j = leaderboard.size() - 1; j > i; j--) {
                        Place oldPlace = leaderboard.get(j - 1);
                        oldPlace.setPlace(j + 1);
                        leaderboard.set(j, oldPlace);
                    }
                }
                leaderboard.set(i, new Place(i + 1, yourName, (long) yourScore));
                overwriteDB();
                break;
            }
        }
        updateUI(newPlace);
    }

    private void overwriteDB() {
        Map<String, Object> map = new HashMap<String, Object>();
        ArrayList first = new ArrayList();
        first.add(leaderboard.get(0).getName());
        first.add((long) leaderboard.get(0).getScore());
        map.put("firstPlace", first);
        ArrayList second = new ArrayList();
        second.add(leaderboard.get(1).getName());
        second.add((long) leaderboard.get(1).getScore());
        map.put("secondPlace", second);
        ArrayList third = new ArrayList();
        third.add(leaderboard.get(2).getName());
        third.add((long) leaderboard.get(2).getScore());
        map.put("thirdPlace", third);
        ArrayList fourth = new ArrayList();
        fourth.add(leaderboard.get(3).getName());
        fourth.add((long) leaderboard.get(3).getScore());
        map.put("fourthPlace", fourth);
        ArrayList fifth = new ArrayList();
        fifth.add(leaderboard.get(4).getName());
        fifth.add((long) leaderboard.get(4).getScore());
        map.put("fifthPlace", fifth);
        ArrayList sixth = new ArrayList();
        sixth.add(leaderboard.get(5).getName());
        sixth.add((long) leaderboard.get(5).getScore());
        map.put("sixthPlace", sixth);
        ArrayList seventh = new ArrayList();
        seventh.add(leaderboard.get(6).getName());
        seventh.add((long) leaderboard.get(6).getScore());
        map.put("seventhPlace", seventh);
        ArrayList eight = new ArrayList();
        eight.add(leaderboard.get(7).getName());
        eight.add((long) leaderboard.get(7).getScore());
        map.put("eightPlace", eight);
        ArrayList ninth = new ArrayList();
        ninth.add(leaderboard.get(8).getName());
        ninth.add((long) leaderboard.get(8).getScore());
        map.put("ninthPlace", ninth);
        ArrayList tenth = new ArrayList();
        tenth.add(leaderboard.get(9).getName());
        tenth.add((long) leaderboard.get(9).getScore());
        map.put("tenthPlace", tenth);

        leaderboardDB.update(map);
    }

    private void updateUI(int place) {
        if (place != -1) {
            TextView description = findViewById(R.id.lblHallOfFame);
            description.setText("Wow, that is incredible, you made it to the hall of Fame! Your current place is " + place);
        }
        RecyclerView leaderboardView = (RecyclerView) findViewById(R.id.history);
        LeaderboardAdapter adapter = new LeaderboardAdapter(leaderboard);
        leaderboardView.setAdapter(adapter);
        leaderboardView.setLayoutManager(new LinearLayoutManager(this));
    }
}
