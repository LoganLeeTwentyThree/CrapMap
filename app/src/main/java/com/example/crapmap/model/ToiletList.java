package com.example.crapmap.model;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToiletList extends List
{

    private Context context;
    private ArrayList<ToiletProfile> toiletList;

    private static boolean firstLoad = true;

    public ToiletList(Context context)
    {
        this.context = context;
        toiletList = new ArrayList<ToiletProfile>();
        load();
    }



    private void load()
    {
        try {

            File file = new File(context.getFilesDir(), "ToiletList.csv");

            file.createNewFile();//in case it doesnt already exist

            Scanner scanner = new Scanner(file);

            while ((scanner.hasNext())) {
                String row = scanner.nextLine();

                String[] tokens = row.split(",");



                //imageID, id, name, avgRating, locX, locY
                ToiletProfile newToilet = new ToiletProfile(
                        Integer.parseInt(tokens[0]),
                        Integer.parseInt(tokens[1]),
                        tokens[2],
                        Float.parseFloat(tokens[3]),
                        new float[]{Float.parseFloat(tokens[4]), Float.parseFloat(tokens[5])}
                );

                toiletList.add(newToilet);



                if(!scanner.hasNext())//if this is the last recorded user in the csv
                {
                    if( firstLoad )// and this is the first time the UserList has been accessed this process
                    {
                        ToiletProfile.setMostRecentID(Integer.parseInt(tokens[1]));//set the most recent id
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ToiletProfile getToiletByID(int id ) throws NotFoundException
    {

        for( ToiletProfile toilet : toiletList )
        {
            if( toilet.getID() == id )
            {
                return toilet;
            }
        }

        throw new NotFoundException("Toilet not found");
    }

    public void addToiletToCSV(ToiletProfile toiletProfile) {
        try {
            File file = new File(context.getFilesDir(), "ToiletList.csv");
            FileWriter writer = new FileWriter(file, true);

            toiletList.add(toiletProfile);

            String toAdd = toiletProfile.getImageID() + "";//bleh
            toAdd += "," + toiletProfile.getID();
            toAdd += "," + toiletProfile.getName();
            toAdd += "," + toiletProfile.getAverageRating();
            toAdd += "," + toiletProfile.getLatlong()[0];
            toAdd += "," + toiletProfile.getLatlong()[1];

            writer.append(toAdd + "\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ToiletProfile> getToiletList() {
        return toiletList;
    }


}
