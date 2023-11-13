package com.example.crapmap.model;

import java.io.*;
import java.util.ArrayList;

public class UserList {
    private File csvFile;
    private ArrayList<UserProfile> userList;

    public UserList(String filePath) throws IOException {
        this.csvFile = new File(filePath);
        if (!csvFile.exists()) {
            csvFile.createNewFile();
        }
        this.userList = new ArrayList<>();
    }

    public UserProfile getByName(String name) {
        for (UserProfile user : userList) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public UserProfile getById(int id) {
        for (UserProfile user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public File getCsvFile() {
        return csvFile;
    }

    // Example of linear search
    public boolean contains(UserProfile userProfile) {
        return userList.contains(userProfile);
    }

    // Other methods related to adding, removing, and manipulating user profiles
}
