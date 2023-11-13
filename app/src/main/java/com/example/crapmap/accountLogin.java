package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;
import javax.swing.*;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import android.content.res.AssetManager;
import android.os.Bundle;

import com.example.crapmap.R;
import com.example.crapmap.model.UserList;

public class accountLogin extends AppCompatActivity {




        // Logic to display the list of users from the user list and allow user selection
        public void displayUserList() {
            try {
                UserList userList = new UserList("userList.csv");
                AssetManager manager = this.getAssets();
                InputStream csvReader = manager.open(userList.getCsvFile());

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
        }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);

        displayUserList();
    }
}