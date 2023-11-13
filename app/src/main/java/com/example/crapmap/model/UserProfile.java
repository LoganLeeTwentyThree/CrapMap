package com.example.crapmap.model;

import java.util.ArrayList;

public class UserProfile {
    private String name;
    private String password;
    private ArrayList<String> timeSpent;
    private int id;

    public UserProfile(String name, String password, int id) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.timeSpent = new ArrayList<>();
    }

    // Getters and setters for the fields
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    // Other methods related to time spent, password management, etc.
}
