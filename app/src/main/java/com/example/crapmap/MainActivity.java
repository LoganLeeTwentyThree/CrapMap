package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crapmap.model.Rating;
import com.example.crapmap.model.RatingList;
import com.example.crapmap.model.ToiletList;
import com.example.crapmap.model.ToiletProfile;
import com.example.crapmap.model.UserList;
import com.example.crapmap.model.UserProfile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLists();//ensures that there are in fact csv files for all relevant data

        Button userSelectButton = findViewById(R.id.userSelectButton);
        Button ProfileButton = findViewById(R.id.profileButton);
        Button toiletListButton = findViewById(R.id.toiletListButton);
        Button mapButton = findViewById(R.id.mapButton);
        userSelectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity.java", "clicked userSelectButton");
                 Intent intent = new Intent(MainActivity.this, accountLogin.class);
                startActivity(intent);
            }
        });
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
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity.java", "clicked mapButton");
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeLists()
    {
        UserList user = new UserList(this);
        ToiletList toiletList = new ToiletList(this);
        RatingList ratingList = new RatingList(this);
    }
}