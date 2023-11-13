package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileWriter;
import java.io.IOException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.crapmap.model.UserList;
import com.example.crapmap.model.UserProfile;

public class accountSignup extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_signup);



    }

    @Override
    public void onClick(View view) {

        if( view.getTag().equals("Submit") )
        {
            EditText editText = findViewById(R.id.editTextUserName);
            UserProfile newUser = new UserProfile(editText.getText().toString(), UserProfile.getID(), new int[7]);
            try {
                UserList userList = new UserList(this);
                userList.addUserToCSV(newUser);
            }catch( IOException e) {
                e.printStackTrace();
            }

        }
    }
}



