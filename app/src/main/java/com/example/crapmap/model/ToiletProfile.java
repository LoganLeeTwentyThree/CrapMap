package com.example.crapmap.model;

import com.example.crapmap.R;

public class ToiletProfile extends Profile{
    private int imageID = R.drawable.chisholm_hall_855;
    private int ID;
    private static int mostRecentID;
    private String name;
    private float averageRating;

    public ToiletProfile(int imageID, int id, String name, float averageRating)
    {
        this.averageRating = averageRating;
        this.name = name;
        this.imageID = imageID;
        ID = id;
    }

    public int getImageID() {
        return imageID;
    }

    public static int getNewID() {
        mostRecentID++;
        return mostRecentID;
    }

    public static void setMostRecentID(int id) {
        mostRecentID = id;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }
}
