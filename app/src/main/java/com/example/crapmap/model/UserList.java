package com.example.crapmap.model;

import android.content.Context;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList {
    private ArrayList<UserProfile> userList;
    private static boolean firstLoad = true;

    private Context context;
    public UserList(Context loading) {
        context = loading;
        this.userList = new ArrayList<>();
        loadUserList("UserList.csv");



    }

    public UserProfile getUserByName(String name) throws NotFoundException{
        for (UserProfile user : userList) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new NotFoundException("No user matches given name");
    }

    public UserProfile getUserById(int id) throws NotFoundException {
        for (UserProfile user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new NotFoundException("No user matches given ID");
    }

    private void loadUserList(String Filename) {
        try {

            File file = new File(context.getFilesDir(), "UserList.csv");

            file.createNewFile();//in case it doesnt already exist

            Scanner scanner = new Scanner(file);

            while ((scanner.hasNext())) {
                String row = scanner.nextLine();

                String[] tokens = row.split(",");
                int[] timeSpentByDay = new int[7];
                for (int i = 2; i < 9; i++) {
                    timeSpentByDay[i - 2] = Integer.parseInt(tokens[i]);
                }

                //name, id, timeSpent0, 1, ...7
                UserProfile newUser = new UserProfile(tokens[0], Integer.parseInt(tokens[1]), timeSpentByDay);

                userList.add(newUser);



                if(!scanner.hasNext())//if this is the last recorded user in the csv
                {
                    if( firstLoad )// and this is the first time the UserList has been accessed this process
                    {
                        UserProfile.setMostRecentID(Integer.parseInt(tokens[1]));//set the most recent id
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addUserToCSV(UserProfile userProfile) {
        try {
            File file = new File(context.getFilesDir(), "UserList.csv");
            FileWriter writer = new FileWriter(file, true);

            String toAdd = userProfile.getName();
            toAdd += "," + userProfile.getId();

            for( int i = 0; i < 7; i++ )
            {
                toAdd += "," + userProfile.getTimeSpent()[i];
            }

            writer.append(toAdd + "\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserProfile> getUserList() {
        return userList;
    }
}
