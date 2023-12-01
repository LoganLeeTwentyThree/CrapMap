package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crapmap.model.RatingList;
import com.example.crapmap.model.ToiletList;
import com.example.crapmap.model.UserList;
import com.example.crapmap.model.UserProfile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity.java", "1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity.java", "2");
        initializeLists();//ensures that there are in fact csv files for all relevant data
        Log.d("MainActivity.java", "2.5");
        Button userSelectButton = findViewById(R.id.userSelectButton);
        Button ProfileButton = findViewById(R.id.profileButton);
        Button toiletListButton = findViewById(R.id.toiletListButton);
        Button mapButton = findViewById(R.id.mapButton);
        Log.d("MainActivity.java", "3");
        userSelectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity.java", "clicked userSelectButton");
                 Intent intent = new Intent(MainActivity.this, AccountLoginActivity.class);
                startActivity(intent);
            }
        });
        Log.d("MainActivity.java", "4");
        ProfileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity.java", "clicked ProfileButton");

                if(UserProfile.getCurrentUser() == null)
                {
                    Toast.makeText(MainActivity.this, "Select a user!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                intent.putExtra("User", UserProfile.getCurrentUser().getId());
                startActivity(intent);
            }
        });
        Log.d("MainActivity.java", "5");
        toiletListButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity.java", "clicked toiletListButton");
                if(UserProfile.getCurrentUser() == null)
                {
                    Toast.makeText(MainActivity.this, "Select a user!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, ToiletListActivity.class);
                startActivity(intent);
            }
        });
        Log.d("MainActivity.java", "6");
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity.java", "clicked mapButton");
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }

    //checks all lists on first run
    private void initializeLists()
    {
        Log.d("MainActivity.java", "init_lists: 1");
        UserList user = new UserList(this);
        Log.d("MainActivity.java", "init_lists: 2");
        ToiletList toiletList = new ToiletList(this);
        Log.d("MainActivity.java", "init_lists: 3");
        RatingList ratingList = new RatingList(this);
        Log.d("MainActivity.java", "init_lists: 4");
    }
}