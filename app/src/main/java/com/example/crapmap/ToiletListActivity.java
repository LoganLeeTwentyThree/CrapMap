package com.example.crapmap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crapmap.model.ToiletList;
import com.example.crapmap.model.ToiletProfile;

public class ToiletListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toilet_list);


        ToiletList toiletList = new ToiletList(this);

        for( ToiletProfile toilet : toiletList.getToiletList() )
        {

            LinearLayout parent = findViewById(R.id.toiletListParent);

            //Container for single toilet entry
            CardView cardView = new CardView(this);
            LinearLayout.LayoutParams cardViewParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    220
            );
            cardViewParams.setMargins(0,10,0,10);
            cardView.setLayoutParams(cardViewParams);
            cardView.setCardBackgroundColor(Color.parseColor("#808080"));
            parent.addView(cardView);

            //Layout for a single toilet entry
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new ViewGroup.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            ));
            linearLayout.setGravity(Gravity.CENTER_VERTICAL);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            cardView.addView(linearLayout);

            /*
                <androidx.cardview.widget.CardView
                        android:id="@+id/cardView3"
                        android:layout_width="75dp"
                        android:layout_height="75dp"
                        app:cardCornerRadius="37.5dp">

                        <ImageView
                            android:id="@+id/imageView2"
                            android:layout_width="75dp"
                            android:layout_height="75dp"
                            app:srcCompat="@drawable/sample_toilet_01_bad" />
                    </androidx.cardview.widget.CardView>

             */

            CardView imageContainer = new CardView(this);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                200,
                200
            );
            imageParams.leftMargin = 50;
            imageContainer.setLayoutParams(imageParams);
            imageContainer.setRadius(100);
            linearLayout.addView(imageContainer);

            ImageView toiletImage = new ImageView(this);
            toiletImage.setLayoutParams(new ViewGroup.LayoutParams(
                    200,
                    200
            ));
            toiletImage.setImageResource(R.drawable.chisholm_hall_855);
            imageContainer.addView(toiletImage);

            LinearLayout nameAndRating = new LinearLayout(this);
            nameAndRating.setLayoutParams( new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            nameAndRating.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(nameAndRating);

            /*<TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginLeft="15dp"
            android:text="Bucket" */

            TextView name = new TextView(this);
            name.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            name.setGravity(Gravity.CENTER);
            name.setPadding(15,0,0,0);
            name.setText(toilet.getName());
            nameAndRating.addView(name);

            /*
            <RatingBar
                        android:id="@+id/ratingBar"
                        style="@style/Widget.AppCompat.RatingBar.Small"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center_vertical"
                        android:numStars="5"
                        android:rating="1"
                        android:layout_marginLeft="15dp"/>
             */

            RatingBar ratingBar = new RatingBar(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.weight = 1;
            ratingBar.setLayoutParams(params);
            ratingBar.setIsIndicator(true);
            ratingBar.setNumStars(5);
            ratingBar.setRating(toilet.getAverageRating());
            ratingBar.setPadding(15,0,0,0);

            nameAndRating.addView(ratingBar);


        }



        ImageButton back_btn = findViewById(R.id.back_btn);
        ImageButton plus_btn = findViewById(R.id.plus_btn);

        // back button
        back_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("ToiletListActivity.java", "clicked back button");
                Intent intent = new Intent(ToiletListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // plus button
        plus_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("ToiletListActivity.java", "clicked plus button");
                Intent intent = new Intent(ToiletListActivity.this, AddToiletActivity.class);
                intent.putExtra("Set", false);
                startActivity(intent);
            }
        });


    }
}