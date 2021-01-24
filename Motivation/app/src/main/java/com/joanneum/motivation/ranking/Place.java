package com.joanneum.motivation.ranking;


public class Place {

    private int place;
    private String name;
    private int score;

    public Place(int place, String name, Long score) {
        this.place = place;
        this.name = name;
        this.score = score.intValue();
    }

    public int getPlace() {
        return place;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setPlace(int place) {
        this.place = place;
    }

}
