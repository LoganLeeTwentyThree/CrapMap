package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });
        toiletListButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity.java", "clicked toiletListButton");
                Intent intent = new Intent(MainActivity.this, ToiletListActivity.class);
                startActivity(intent);
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity.java", "clicked mapButton");
                // TODO: make mapview activity class thing
                // Intent intent = new Intent(MainActivity.this, ToiletListActivity.class);
                // startActivity(intent);
            }
        });
    }
}