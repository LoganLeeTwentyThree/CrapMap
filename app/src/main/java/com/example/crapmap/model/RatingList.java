package com.example.crapmap.model;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RatingList {
    private ArrayList<Rating> allRatings;
    private Context context;

    public RatingList(Context context)
    {
        allRatings = new ArrayList<Rating>();
        this.context = context;
        load();
    }

    public Rating getRatingByUser( UserProfile user ) throws NotFoundException
    {
        for( int i = 0; i < allRatings.size(); i++)
        {
            if( allRatings.get(i).getRater().equals(user) )
            {
                return allRatings.get(i);
            }
        }

        throw new NotFoundException("User not in list");
    }

    public Rating getRatingByToilet( ToiletProfile toilet ) throws NotFoundException
    {
        for( int i = 0; i < allRatings.size(); i++)
        {
            if( allRatings.get(i).getRatee().equals(toilet) )
            {
                return allRatings.get(i);
            }
        }

        throw new NotFoundException("Toilet not in list");
    }


    // RaterID, RateeID, NumStars, Review
    public void load()
    {
        try
        {
            File file = new File(context.getFilesDir(), "RatingList.csv");
            file.createNewFile();//in case it doesn't exist
            Scanner scanner = new Scanner(file);



            UserList userList = new UserList(context);
            ToiletList toiletList = new ToiletList(context);
            while( scanner.hasNext() )
            {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");

                try
                {
                    UserProfile user = userList.getUserById(Integer.parseInt(tokens[0]));
                    ToiletProfile toilet = toiletList.getToiletByID(Integer.parseInt(tokens[1]));
                    allRatings.add( new Rating(user, toilet, Float.parseFloat(tokens[2]), tokens[3]));
                }catch(Exception e)
                {
                    System.out.println("FILE ERROR");
                    e.printStackTrace();
                }


            }

        }catch(IOException e)
        {
            e.printStackTrace();
        }

    }




    public ArrayList<Rating> getAllRatings() {
        return allRatings;
    }

    public void addRatingToCSV(Rating rating) {
        try {
            File file = new File(context.getFilesDir(), "RatingList.csv");
            FileWriter writer = new FileWriter(file, true);

            allRatings.add(rating);

            String toAdd = rating.getRater().getId() + "";//bleh
            toAdd += "," + rating.getRatee().getID();
            toAdd += "," + rating.getNumStars();
            toAdd += "," + rating.getReview();

            writer.append(toAdd + "\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
