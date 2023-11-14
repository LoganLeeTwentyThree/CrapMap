package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.crapmap.R;
import com.example.crapmap.model.NotFoundException;
import com.example.crapmap.model.UserList;
import com.example.crapmap.model.UserProfile;

public class accountLogin extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);


        UserList userList = new UserList(this);
        LinearLayout scrollView = findViewById(R.id.accountSelect);
        System.out.println("-------" + userList.getUserList().size());
        for( int i = 0; i < userList.getUserList().size(); i++ )
        {
            Button button = new Button(this);
            button.setText(userList.getUserList().get(i).getName());
            button.setOnClickListener(this);
            button.setTag("userButton");
            scrollView.addView(button);

        }


        findViewById(R.id.addNew).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if( view.getTag().equals("addNew") ) {
            Intent intent = new Intent(this, accountSignup.class);
            startActivity(intent);
        }else if (view.getTag().equals("userButton"))
        {
            Button button = (Button) view;
            UserList userList = new UserList(this);
            try {

                UserProfile userSelected = userList.getUserByName(button.getText().toString());
                UserProfile.setCurrentUser(userSelected);
                finish();
            } catch (NotFoundException e) {
                e.printStackTrace();
            }




        }



    }
}