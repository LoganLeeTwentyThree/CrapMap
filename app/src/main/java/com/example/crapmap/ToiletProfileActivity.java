package com.example.crapmap;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crapmap.model.NotFoundException;
import com.example.crapmap.model.RatingList;
import com.example.crapmap.model.ToiletList;
import com.example.crapmap.model.ToiletProfile;

public class ToiletProfileActivity  extends AppCompatActivity {

    int currentToiletID;
    ToiletProfile currentToilet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toilet_profile);

        currentToiletID = (int)getIntent().getExtras().get("Toilet");

        try{
            currentToilet = new ToiletList(this).getToiletByID(currentToiletID);
        }catch(NotFoundException e)
        {
            e.printStackTrace();
        }

        ScrollView scrollView = findViewById(R.id.toiletProfileScrollView);

        RatingList ratingList = new RatingList(this);

        for( int i = 0; i < ratingList.getAllRatings().size(); i++ )
        {
            if( !ratingList.getAllRatings().get(i).getRatee().equals(currentToilet) )
                continue;

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT
                    ));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);


            ImageView profilePic = new ImageView(this);
            profilePic.setImageResource(R.drawable.johnsmith);

            linearLayout.addView(profilePic);

            TextView textView = new TextView(this);
            textView.setText(ratingList.getAllRatings().get(i).getReview());


            scrollView.addView(linearLayout);
        }

    }








}
