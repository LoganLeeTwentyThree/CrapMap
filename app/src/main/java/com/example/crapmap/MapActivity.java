package com.example.crapmap;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;

import com.example.crapmap.model.NotFoundException;
import com.example.crapmap.model.ToiletList;
import com.example.crapmap.model.ToiletProfile;

import java.util.ArrayList;
import android.graphics.drawable.Drawable;


public class MapActivity extends AppCompatActivity implements View.OnTouchListener {
    private boolean set = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        ImageView mapView = findViewById(R.id.map);
        mapView.setOnTouchListener(this);

        if( getIntent().getExtras() != null && getIntent().getExtras().get("Set").equals(true))
        {
            set = true;
        }

        ToiletList toiletlist = new ToiletList(this);
        loadPins(toiletlist.getToiletList());

    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN && set)
        {
            ImageView map = findViewById(R.id.map);
            if(view.getTag() != null){
                if( view.getTag().equals(("map"))){
                    int id = (int)getIntent().getExtras().get("ID");

                    Log.d("MapActivity.java", "touched: " + Float.toString((float)motionEvent.getX()) + "," + Float.toString((float)motionEvent.getY()));
                    if (true == false) {
                        return true;
                    }
                    Intent intent = new Intent(this, AddToiletActivity.class);
                    intent.putExtra("Name", (String)getIntent().getExtras().get("Name"));
                    intent.putExtra("X", (float)motionEvent.getX());
                    intent.putExtra("Y", (float)motionEvent.getY());
                    intent.putExtra("Rating", (float)getIntent().getExtras().get("Rating"));
                    intent.putExtra("Review", (SpannableString)getIntent().getExtras().get("Review"));
                    intent.putExtra("ID",id);
                    intent.putExtra("FromMap",true);
                    startActivity(intent);
                }
            }



        }
        return false;
    }

    public void loadPins(ArrayList<ToiletProfile> toilets) {
        ConstraintLayout parent = (ConstraintLayout)findViewById(R.id.mapRoot);
        for (ToiletProfile toilet : toilets) {
            float[] flatlong = toilet.getLatlong();
            int[] ilatlong = {Math.round(flatlong[0]), Math.round(flatlong[1])};
            Log.d("MapActivity.java", Float.toString(ilatlong[0]) + "," + Float.toString(ilatlong[1]));
            ImageView newpin = new ImageView(this);
            newpin.setImageResource(R.drawable._85px_google_maps_pin_svg__2_);
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.topMargin = Math.round(ilatlong[1]) - 85; // 35 is height of pin image
            layoutParams.leftMargin = Math.round(ilatlong[0]) - 20; // 20 is height of pin image
            newpin.setLayoutParams(layoutParams);
            newpin.setOnClickListener(v -> {
                Intent intent = new Intent(this, ToiletProfileActivity.class);
                intent.putExtra("Toilet", toilet.getID());
                startActivity(intent);
            });
            parent.addView(newpin);

        }
    }
}
