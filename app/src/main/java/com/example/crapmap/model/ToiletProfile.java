package com.example.crapmap.model;

import android.util.Log;

import com.example.crapmap.R;

public class ToiletProfile extends Profile{
    private int imageID = R.drawable.chisholm_hall_855;
    private int ID;
    private static int mostRecentID;
    private float averageRating;
    private float[] latlong = new float[2];

    public ToiletProfile(int imageID, int id, String name, float averageRating, float[] latlong)
    {
        super(name);
        this.averageRating = averageRating;
        this.imageID = imageID;
        ID = id;
        this.latlong[0] = latlong[0];
        this.latlong[1] = latlong[1];
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

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public boolean equals(ToiletProfile other) {
        Log.d("ToiletProfile.java", this.getName() + " == " + other.getName() + " ? " + (this.getID() == other.getID()));
        return this.getID() == other.getID();
    }

    public void setLatLong(float[] latLong)
    {
        this.latlong = latlong;
    }

    public float[] getLatlong() {
        return latlong;
    }
}
