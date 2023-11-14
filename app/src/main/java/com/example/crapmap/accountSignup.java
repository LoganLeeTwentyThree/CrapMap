package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileWriter;
import java.io.IOException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crapmap.model.NotFoundException;
import com.example.crapmap.model.UserList;
import com.example.crapmap.model.UserProfile;

public class accountSignup extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_signup);

        findViewById(R.id.submitButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if( view.getTag().equals("Submit") )
        {
            EditText editText = findViewById(R.id.editTextUserName);
            UserProfile newUser = new UserProfile(editText.getText().toString(), UserProfile.getNewID(), new int[7]);
            UserList userList = new UserList(this);
            try {

                userList.getUserByName(newUser.getName());
                Toast.makeText(this, "User already exists!", Toast.LENGTH_SHORT).show();

            }catch( NotFoundException e) {
                userList.addUserToCSV(newUser);
            }

            finish();

        }
    }
}



