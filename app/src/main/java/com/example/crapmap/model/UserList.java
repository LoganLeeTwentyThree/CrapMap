package com.example.crapmap.model;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList {
    private File csvFile;
    private ArrayList<UserProfile> userList;

    public UserList(String filePath, Context Loading) throws IOException {
        loadUserList(Loading, filePath);
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

    public void loadUserList(Context Loading, String Filename) {
        try {

            AssetManager manager = Loading.getAssets();
            InputStream csvReader = manager.open(Filename);

            Scanner scanr = new Scanner(csvReader);
            while ((scanr.hasNext()) ) {
                String row = scanr.nextLine();
                String[] data = row.split(",");
                // Display the data in the UI or console
                System.out.println("Name: " + data[0] + ", ID: " + data[1]);
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    // Example of linear search
    public boolean contains(UserProfile userProfile) {
        return userList.contains(userProfile);
    }



    // Other methods related to adding, removing, and manipulating user profiles
}
