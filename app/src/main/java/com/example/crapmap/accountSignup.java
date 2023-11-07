package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileWriter;
import java.io.IOException;
import android.os.Bundle;

public class accountSignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_signup);


        public class AddUser_Activity {

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
    }
}