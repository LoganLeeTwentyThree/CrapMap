package com.example.crapmap;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

        ImageButton backButton = findViewById(R.id.addToiletBack);
        backButton.setOnClickListener(this);

        Button submit = findViewById(R.id.addToiletSubmitButton);
        submit.setOnClickListener(this);

        if( getIntent().getExtras().get("FromMap") != null )
        {
            if( (Boolean)getIntent().getExtras().get("FromMap") ){

                saveNewToilet();
            }
        }
    }


    @Override
    public void onClick(View view) {
        if(view.getTag().equals("submit"))
        {

            EditText nameField = findViewById(R.id.toiletName);
            RatingBar bar = findViewById(R.id.addToiletRatingBar);
            EditText review = findViewById(R.id.addToiletReview);

            if(review.getText().toString().contains(",") || review.getText().toString().contains("\n"))
            {
                //I really don't have the time to do this
                Toast.makeText(this, "No Commas or newlines >:(", Toast.LENGTH_LONG).show();
                return;
            }


            Intent intent = new Intent(this, MapActivity.class);
            intent.putExtra("Set", true);
            intent.putExtra("Name", nameField.getText().toString());
            intent.putExtra("ID", ToiletProfile.getNewID());
            intent.putExtra("Rating", bar.getRating());
            intent.putExtra("Review", review.getText());
            startActivity(intent);

        }else if(view.getTag().equals("back"))
        {
            Intent intent = new Intent(this, ToiletListActivity.class);
            startActivity(intent);
        }

    }

    private void saveNewToilet()
    {


        int id = (int)getIntent().getExtras().get("ID");
        float ratingNum = (float)getIntent().getExtras().get("Rating");
        String review = getIntent().getExtras().get("Review").toString();
        float x = (float)getIntent().getExtras().get("X");
        float y = (float)getIntent().getExtras().get("Y");

        String name = getIntent().getExtras().get("Name").toString();

        ToiletProfile newToilet = new ToiletProfile(
                R.drawable.chisholm_hall_855,
                id,
                name,
                ratingNum,
                new float[]{x,y}
        );

        ToiletList toiletList = new ToiletList(this);

        toiletList.addToiletToCSV(newToilet);

        RatingList ratingList = new RatingList(this);
        Rating rating = new Rating(
                UserProfile.getCurrentUser(),
                newToilet,
                ratingNum,
                review
        );
        ratingList.addRatingToCSV(rating);

        Intent intent = new Intent(this, ToiletListActivity.class);
        startActivity(intent);



    }

}