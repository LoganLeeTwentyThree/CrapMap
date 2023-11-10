package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import android.os.Bundle;

import com.example.crapmap.R;

public class accountLogin extends AppCompatActivity {


    public class UserSelect_Activity {

        // Logic to display the list of users from the user list and allow user selection
        public void displayUserList(UserList userList) {
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(userList.getCsvFile()));
                String row;
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    // Display the data in the UI or console
                    System.out.println("Name: " + data[0] + ", ID: " + data[2]);
                }
                csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);
    }
}