package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ToiletListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toilet_list);

        ImageButton back_btn = findViewById(R.id.back_btn);
        ImageButton plus_btn = findViewById(R.id.plus_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("ToiletListActivity.java", "clicked back button");
            }
        });

        plus_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("ToiletListActivity.java", "clicked plus button");
            }
        });

        // TODO: find a way to procedurally add buttons to each element in the list, and set it up so they'll launch the respective profiles
    }
}