package com.example.crapmap;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
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

        ToiletList toiletList = new ToiletList(this);
        for(ToiletProfile toilet : toiletList.getToiletList())
        {
            if(toilet.getLatlong()[0] != 0)
            {
                ImageView pin = new ImageView(this);
                pin.setImageResource(R.drawable._85px_google_maps_pin_svg__2_);

                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );


                pin.setLayoutParams(params);
                ConstraintLayout root = findViewById(R.id.mapRoot);
                //pin.setId();
                root.addView(pin);

                ConstraintLayout constraintLayout = findViewById(R.id.mapRoot);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(pin.getId(),ConstraintSet.LEFT,R.id.mapRoot,ConstraintSet.RIGHT,(int)toilet.getLatlong()[0]);
                constraintSet.connect(pin.getId(),ConstraintSet.TOP,R.id.mapRoot,ConstraintSet.TOP,(int)toilet.getLatlong()[1]);
                constraintSet.applyTo(constraintLayout);
            }
        }

    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN && set)
        {
            ImageView map = findViewById(R.id.map);
            if(view.getTag() != null){
                if( view.getTag().equals(("map"))){
                    int id = (int)getIntent().getExtras().get("ID");

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
}
