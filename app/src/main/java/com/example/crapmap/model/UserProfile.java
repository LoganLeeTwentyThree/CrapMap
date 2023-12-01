package com.example.crapmap.model;

import java.util.ArrayList;

public class UserProfile extends Profile {
    private int[] timeSpent;
    private int id;
    private static int mostRecentID;//for assigning new ids to new users
    private static UserProfile currentUser;

    public UserProfile(String name, int id, int[] timeSpent) {
        super(name);
        this.id = id;
        this.timeSpent = timeSpent;
    }

    public static UserProfile getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserProfile currentUser) {
        UserProfile.currentUser = currentUser;
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

    public static int getNewID()
    {
        mostRecentID++;
        return mostRecentID;
    }


}
