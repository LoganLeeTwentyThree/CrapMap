package com.example.crapmap;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.*;

import com.example.crapmap.model.NotFoundException;
import com.example.crapmap.model.Rating;
import com.example.crapmap.model.RatingList;
import com.example.crapmap.model.ToiletList;
import com.example.crapmap.model.ToiletProfile;
import com.example.crapmap.model.UserProfile;

public class AddToiletActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_toilet);

        Button imageSubmitButton = findViewById(R.id.uploadImage);
        imageSubmitButton.setOnClickListener(this);

        Button submit = findViewById(R.id.addToiletSubmitButton);
        submit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if( view.getTag().equals("uploadImage") )
        {

            /* Registers a photo picker activity launcher in single-select mode.
            ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
                    registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                        // Callback is invoked after the user selects a media item or closes the
                        // photo picker.
                        if (uri != null) {
                            Log.d("PhotoPicker", "Selected URI: " + uri);
                        } else {
                            Log.d("PhotoPicker", "No media selected");
                        }
                    });

            // Launch the photo picker and let the user choose only images.
            pickMedia.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build());
        */
            //TODO: re evaluate whether we actually can implement images for profiles (Causes gradle issues :))

        }else if(view.getTag().equals("submit"))
        {

            EditText nameField = findViewById(R.id.toiletName);
            RatingBar bar = findViewById(R.id.addToiletRatingBar);
            EditText review = findViewById(R.id.addToiletReview);

            if(review.getText().toString().contains(",") || review.getText().toString().contains("\n") || nameField.getText().toString().contains(",") || nameField.getText().toString().contains("\n"))
            {
                //I really don't have the time to do this
                Toast.makeText(this, "No Commas or newlines >:(", Toast.LENGTH_LONG).show();
                return;
            }

            // just add a rating if name is same as preexisting toilet
            ToiletList toiletList = new ToiletList(this);
            RatingList ratingList = new RatingList(this);

            ToiletProfile toilet;

            try {
                toilet = toiletList.getToiletByName(nameField.getText().toString());
                Log.d("AddToiletActivity.java", "found preexisting toilet");
            } catch (NotFoundException e) {
                Log.d("AddToiletActivity.java", "could not find preexisting toilet");

                toilet = new ToiletProfile(
                        R.drawable.chisholm_hall_855,
                        ToiletProfile.getNewID(),
                        nameField.getText().toString(),
                        bar.getRating(),
                        new float[] {0.0F, 0.0F}
                );

                toiletList.addToiletToCSV(toilet);
            }



            Rating rating = new Rating(
                    UserProfile.getCurrentUser(),
                    toilet,
                    bar.getRating(),
                    review.getText().toString()
                    );
            ratingList.addRatingToCSV(rating);
            finish();
        }

    }
}