package com.example.crapmap.model;

import java.util.ArrayList;

public class UserProfile {
    private String name;
    private int[] timeSpent;
    private int id;
    private static int mostRecentID;//for assigning new ids to new users

    public UserProfile(String name, int id, int[] timeSpent) {
        this.name = name;
        this.id = id;
        this.timeSpent = timeSpent;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int[] getTimeSpent() {
        return timeSpent;
    }

    public static void setMostRecentID(int id)
    {
        mostRecentID = id;
    }

    public static int getID()
    {
        mostRecentID++;
        return mostRecentID;
    }



    // Other methods related to time spent, password management, etc.

}
