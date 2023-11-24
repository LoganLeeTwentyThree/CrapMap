package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.hardware.usb.UsbRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.content.Intent;

import com.example.crapmap.model.NotFoundException;
import com.example.crapmap.model.Rating;
import com.example.crapmap.model.RatingList;
import com.example.crapmap.model.UserList;
import com.example.crapmap.model.UserProfile;

import java.io.IOException;
import java.net.NoRouteToHostException;
import java.util.ArrayList;


public class UserProfileActivity extends AppCompatActivity {
    private UserProfile user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        UserList userlist = new UserList(this);
        try {
            user = userlist.getUserById((int) getIntent().getExtras().get("User"));
        } catch (NotFoundException e) {
            Log.e("UserProfileActivity.java", "Did not recieve user ID as extra (should recieve \"User\": <userid>)");
        }

        ImageButton backBtn = findViewById(R.id.back_btn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



            TextView usernameText = (TextView)findViewById(R.id.username);
            usernameText.setText(user.getName());

            //for setting the averages
            int ids[] = {
                    R.id.mondayLine,
                    R.id.tuesdayLine,
                    R.id.wednesdayLine,
                    R.id.thursdayLine,
                    R.id.fridayLine,
                    R.id.saturdayLine,
                    R.id.sundayLine,

            };

            for( int i = 0; i < 7; i++ )
            {
                findViewById(ids[i]).setLayoutParams(new LinearLayout.LayoutParams(
                        10, 10 * user.getTimeSpent()[i]
                ));
            }

            RatingList ratingList = new RatingList(this);

            ArrayList<Rating> curUserRatings = new ArrayList<Rating>();
            for( Rating rating : ratingList.getAllRatings())
            {
                if(rating.getRater().getId() == user.getId())
                {
                    curUserRatings.add(rating);
                }
            }

            for( Rating rating : curUserRatings )
            {
                CardView cardView = new CardView(this);
                cardView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                ));

                LinearLayout.LayoutParams cardViewParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                cardViewParams.setMargins(0,10,0,10);
                cardView.setLayoutParams(cardViewParams);
                cardView.setCardBackgroundColor(Color.parseColor("#e0e0e0"));

                LinearLayout parent = findViewById(R.id.ratingsBox);
                parent.addView(cardView);


                //container for single rating entry
                LinearLayout ratingEntry = new LinearLayout(this);
                ratingEntry.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        300
                ));
                ratingEntry.setOrientation(LinearLayout.HORIZONTAL);
                ratingEntry.setGravity(Gravity.CENTER);

                cardView.addView(ratingEntry);

                //container for entry image
                CardView cardView2 = new CardView(this);
                cardView2.setLayoutParams(new LinearLayout.LayoutParams(
                        150,
                        150
                ));
                cardView2.setRadius(75);
                cardView2.setPadding(100,0,0,0);

                ratingEntry.addView(cardView2);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.weight = 1;

                //container for name + rating
                LinearLayout nameRatingBox = new LinearLayout(this);
                nameRatingBox.setGravity(Gravity.CENTER);
                nameRatingBox.setLayoutParams( new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                nameRatingBox.setOrientation(LinearLayout.VERTICAL);
                nameRatingBox.setPadding(30,0,0,0);

                ratingEntry.addView(nameRatingBox);


                //toilet image in rating entry
                ImageView toiletImage = new ImageView(this);
                toiletImage.setLayoutParams(params);
                toiletImage.setImageResource(rating.getRatee().getImageID());
                cardView2.addView(toiletImage);

                //Name of rating entry
                TextView textView = new TextView(this);


                textView.setLayoutParams(params);
                textView.setText(rating.getRatee().getName());
                textView.setGravity(Gravity.CENTER);



                nameRatingBox.addView(textView);

                //stars
                RatingBar bar = new RatingBar(this);
                bar.setLayoutParams(params);
                bar.setNumStars(5);
                bar.setIsIndicator(true);
                bar.setRating(rating.getNumStars());


                nameRatingBox.addView(bar);

                // TODO: attempt to make it clickable
                cardView.setOnClickListener(v -> {
                    Log.d("UserProfileActivity.java", "clicked toilet view");
                    Intent intent = new Intent(this, ToiletProfileActivity.class);
                    // TODO: add toilet id as intent thingy
                    intent.putExtra("Toilet", rating.getRatee().getID());
                    startActivity(intent);
                });

            }





    }
}