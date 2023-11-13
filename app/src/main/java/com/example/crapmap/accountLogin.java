package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.crapmap.model.UserList;

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

        Button backButton = findViewById(R.id.backbtn);
        Button newAccountButton = findViewById(R.id.newaccountbtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("accountLogin.java", "clicked backbutton");
                finish();
            }
        });

        newAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("accountLogin.java", "clicked newaccountbutton");
                Intent intent = new Intent(accountLogin.this, accountSignup.class);
                startActivity(intent);
            }
        });

    }
}