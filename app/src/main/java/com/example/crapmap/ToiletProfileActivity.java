package com.example.crapmap;

import static com.example.crapmap.R.drawable.border;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crapmap.model.NotFoundException;
import com.example.crapmap.model.Rating;
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
            Log.d("ToiletProfileActivity.java", "current toilet is: " + currentToilet.getName());
        }catch(NotFoundException e)
        {
            e.printStackTrace();
        }

        TextView title = findViewById(R.id.title);
        title.setText(currentToilet.getName());

        ImageButton plusbtn = findViewById(R.id.plus_btn);
        plusbtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddToiletActivity.class);
            intent.putExtra("ToiletName", currentToilet.getName());
            startActivity(intent);
        });

        LinearLayout parent = findViewById(R.id.toiletProfileListParent);

        RatingList ratingList = new RatingList(this);

        // for each rating
//        for( int i = 0; i < ratingList.getAllRatings().size(); i++ )
//        {
//            // skip if the rating is not related to this toilet
//            if( !ratingList.getAllRatings().get(i).getRatee().equals(currentToilet) ) {
//                Log.d("ToiletProfileActivity.java", "skipping over rating: " + ratingList.getAllRatings().get(i).getRatee().getName());
//                continue;
//            } else {
//                Log.d("ToiletProfileActivity.java", "adding rating: " + ratingList.getAllRatings().get(i).getRatee().getName());
//            }
//
//            LinearLayout linearLayout = new LinearLayout(this);
//            linearLayout.setLayoutParams(
//                    new LinearLayout.LayoutParams(
//                            LinearLayout.LayoutParams.MATCH_PARENT,
//                            LinearLayout.LayoutParams.MATCH_PARENT
//                    ));
//            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//
//            Log.d("ToiletProfileActivity.java", "created linear layout");
//
////            ImageView profilePic = new ImageView(this);
////            profilePic.setImageResource(R.drawable.johnsmith);
//
////            linearLayout.addView(profilePic);
//            Log.d("ToiletProfileActivity.java", "added profile pic");
//            TextView review_textView = new TextView(this);
//            review_textView.setText(ratingList.getAllRatings().get(i).getReview());
//
//            linearLayout.addView(review_textView);
//
//            Log.d("ToiletProfileActivity.java", "about to add to scrollview...");
//
//            parentView.addView(linearLayout);
//
//            Log.d("ToiletProfileActivity.java", "successfully added to scrollview");
//        }

        for( Rating rating : ratingList.getAllRatings() )
        {
            if (rating.getRatee().getID() != currentToiletID) {
                continue;
            }

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
            parent.addView(cardView);

            //container for single rating entry
            LinearLayout ratingEntry = new LinearLayout(this);
            ratingEntry.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    300
            ));
            ratingEntry.setOrientation(LinearLayout.HORIZONTAL);
            ratingEntry.setGravity(Gravity.CENTER_VERTICAL);

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
            ImageView userImage = new ImageView(this);
            userImage.setLayoutParams(params);
            userImage.setImageResource(R.drawable.johnsmith);
            cardView2.addView(userImage);

            //Name of rating entry
            TextView textView = new TextView(this);


            textView.setLayoutParams(params);
            textView.setText(rating.getRater().getName());
            textView.setGravity(Gravity.CENTER);



            nameRatingBox.addView(textView);

            //stars
            RatingBar bar = new RatingBar(this);
            bar.setLayoutParams(params);
            bar.setNumStars(5);
            bar.setIsIndicator(true);
            bar.setRating(rating.getNumStars());


            nameRatingBox.addView(bar);

            // actual rating
            TextView rating_textView = new TextView(this);
            rating_textView.setLayoutParams(params);
            rating_textView.setText(rating.getReview());
            rating_textView.setGravity(Gravity.CENTER);

            nameRatingBox.addView(rating_textView);

            // TODO: attempt to make it clickable
            cardView.setOnClickListener(v -> {
                Log.d("UserProfileActivity.java", "clicked toilet view");
                Intent intent = new Intent(this, UserProfileActivity.class);
                Log.d("UserProfileActivity.java", "1");
                intent.putExtra("User", rating.getRater().getId());
                Log.d("UserProfileActivity.java", "2");
                startActivity(intent);
                Log.d("UserProfileActivity.java", "3");
            });

        }






    }








}
