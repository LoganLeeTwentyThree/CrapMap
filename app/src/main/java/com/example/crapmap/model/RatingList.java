package com.example.crapmap.model;

import static java.lang.System.in;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RatingList extends List
{
    private ArrayList<Rating> allRatings;
    private Context context;

    public RatingList(Context context)
    {
        allRatings = new ArrayList<Rating>();
        this.context = context;
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
            File file = new File(context.getFilesDir(), "RatingsList.csv");
            Scanner scanner = new Scanner(file);

            UserList userList = new UserList(context);
            ToiletList toiletList = new ToiletList();
            while( scanner.hasNext() )
            {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");

                try
                {
                    UserProfile user = userList.getUserById(Integer.parseInt(tokens[0]));
                    ToiletProfile toilet = toiletList.getToiletByID(Integer.parseInt(tokens[1]));
                    allRatings.add( new Rating(user, toilet, Integer.parseInt(tokens[2]), tokens[3]));
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

    public void setAllRatings(ArrayList<Rating> allRatings) {
        this.allRatings = allRatings;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
