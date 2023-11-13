package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.crapmap.R;
import com.example.crapmap.model.UserList;

public class accountLogin extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);

        try{
            UserList userList = new UserList(this);
            for( int i = 0; i < userList.getUserList().size(); i++ )
            {
                Button button = new Button(this);
                button.setText(userList.getUserList().get(i).getName());
                button.setOnClickListener(this);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }



    }

    @Override
    public void onClick(View view) {

    }
}