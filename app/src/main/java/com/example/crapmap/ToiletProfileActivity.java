package com.example.crapmap;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
        Log.d("ToiletProfileActivity.java", "1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toilet_profile);
        Log.d("ToiletProfileActivity.java", "2");

        currentToiletID = (int)getIntent().getExtras().get("Toilet");
        Log.d("ToiletProfileActivity.java", "3");

        try{
            currentToilet = new ToiletList(this).getToiletByID(currentToiletID);
            Log.d("ToiletProfileActivity.java", "current toilet is: " + currentToilet.getName());
        }catch(NotFoundException e)
        {
            e.printStackTrace();
        }

        LinearLayout parentView = findViewById(R.id.toiletProfileListParent);

        RatingList ratingList = new RatingList(this);

        // for each rating
        for( int i = 0; i < ratingList.getAllRatings().size(); i++ )
        {
            // skip if the rating is not related to this toilet
            if( !ratingList.getAllRatings().get(i).getRatee().equals(currentToilet) ) {
                Log.d("ToiletProfileActivity.java", "skipping over rating: " + ratingList.getAllRatings().get(i).getRatee().getName());
                continue;
            } else {
                Log.d("ToiletProfileActivity.java", "adding rating: " + ratingList.getAllRatings().get(i).getRatee().getName());
            }

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT
                    ));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            Log.d("ToiletProfileActivity.java", "created linear layout");

            ImageView profilePic = new ImageView(this);
            profilePic.setImageResource(R.drawable.johnsmith);

            linearLayout.addView(profilePic);
            Log.d("ToiletProfileActivity.java", "added profile pic");
            TextView textView = new TextView(this);
            textView.setText(ratingList.getAllRatings().get(i).getReview());

            Log.d("ToiletProfileActivity.java", "about to add to scrollview...");

            parentView.addView(linearLayout);

            Log.d("ToiletProfileActivity.java", "successfully added to scrollview");
        }

    }








}
