package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileWriter;
import java.io.IOException;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.crapmap.model.UserList;
import android.widget.Button;


public class accountSignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_signup);


        class AddUser_Activity {

            // Logic to take in info from fields and create an entry in the user list CSV
            public void addUserToCSV(UserList userList, String name, String password, int id) {
                try {
                    FileWriter csvWriter = new FileWriter(userList.getCsvFile(), true);
                    csvWriter.append(name);
                    csvWriter.append(",");
                    csvWriter.append(password);
                    csvWriter.append(",");
                    csvWriter.append(String.valueOf(id));
                    csvWriter.append("\n");
                    csvWriter.flush();
                    csvWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Button backButton = findViewById(R.id.back_btn);
        Button submitButton = findViewById(R.id.submit_button);

        backButton.setOnClickListener((View.OnClickListener) v -> {
            Log.d("accountSignup.java", "clicked back button");
            finish();
        });
        submitButton.setOnClickListener((View.OnClickListener) v -> {
            Log.d("accountSignup.java", "clicked submit button");
            // TODO: MAKE NEW USER FROM USER PROVIDE DATA
            Log.w("accountSignup.java", "TODO: make new user from user provided data");
            finish();
        });
    }
}